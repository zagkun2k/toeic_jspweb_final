package com.truonggiang.web.controller.admin;

import com.truonggiang.core.common.utils.ExcelPoiUtil;
import com.truonggiang.core.common.utils.SessionUtil;
import com.truonggiang.core.common.utils.UploadUtil;
import com.truonggiang.core.dto.ImportUserDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.service.UserService;
import com.truonggiang.core.serviceimpl.UserServiceImpl;
import com.truonggiang.core.utils.SingletonDaoUtil;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.core.web.utils.WebCommonUtil;
import com.truonggiang.web.command.UserCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-list-user.html", "/ajax-admin-user-edit.html", "/admin-import-user-list.html" , "/admin-import-user-validated.html"})
public class UserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String SHOW_IMPORT_USER = "import_user";
    private final String READ_EXCEL = "read_excel";
    private final String VALIDATED_IMPORT = "validated_import";
    private final String LIST_USER_IMPORT = "list_user_import";
    private final String IMPORT_DATA = "import_data";
    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand userCommand = FormUtil.populate(UserCommand.class, request);
//        userCommand.setCrudaction(WebConstant.INSERT_UPDATE);
        UserDTO pojo = userCommand.getPojo();

        if (userCommand.getUrlType() != null && userCommand.getUrlType().equals(WebConstant.URL_LIST)) {
            if (userCommand.getCrudaction() != null && userCommand.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<>();
                for (String item : userCommand.getCheckList()) {
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getUserServiceImpl().deleteUser(ids);
                if (result != ids.size()) {
                    userCommand.setCrudaction(WebConstant.NOTHING_MESSAGE);
                }
            }
            Map<String, Object> mapProperty = new HashMap<>();
            RequestUtil.initSearchBeanUtil(request, userCommand);
            Object[] objects = SingletonServiceUtil.getUserServiceImpl().findByProperty(mapProperty, userCommand.getSortExpression(), userCommand.getSortDirection(), userCommand.getFirstItem(), userCommand.getMaxPageItems());

            userCommand.setListResult((List<UserDTO>) objects[1]);
            userCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
            if (userCommand.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapMessageResponse(resourceBundle);
                WebCommonUtil.addRedirectMessage(request, userCommand.getCrudaction(), mapMessage);
            }
            request.setAttribute(WebConstant.LIST_ITEMS, userCommand);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user/list.jsp");
            dispatcher.forward(request, response);
        } else if (userCommand.getUrlType() != null && userCommand.getUrlType().equals(WebConstant.URL_EDIT)) {

            if (pojo != null && pojo.getUserId() != null) {
                userCommand.setPojo(SingletonServiceUtil.getUserServiceImpl().findById(pojo.getUserId()));
            }
            userCommand.setRoles(SingletonServiceUtil.getRoleServiceImpl().findAll());
            request.setAttribute(WebConstant.FORM_ITEM, userCommand);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user/edit.jsp");
            dispatcher.forward(request, response);
        } else if (userCommand.getUrlType() != null && userCommand.getUrlType().equals(SHOW_IMPORT_USER)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            dispatcher.forward(request, response);
        } else if (userCommand.getUrlType() != null && userCommand.getUrlType().equals(VALIDATED_IMPORT)) {
            List<ImportUserDTO> importUserDTOS = (List<ImportUserDTO>) SessionUtil.getSession().getValue(request, LIST_USER_IMPORT);
            userCommand.setImportUserDTOS(importUserDTOS);
            userCommand.setMaxPageItems(3);
            userCommand.setTotalItems(importUserDTOS.size());

            request.setAttribute(WebConstant.LIST_ITEMS, userCommand);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            dispatcher.forward(request, response);
        }
    }

    private Map<String, String> buildMapMessageResponse(ResourceBundle resourceBundle) {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.user.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.user.message.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.user.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        mapMessage.put(WebConstant.NOTHING_MESSAGE, resourceBundle.getString("label.message.nothing"));
        return mapMessage;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> value = new HashSet<>();
        value.add("urlType");
        Object[] objects = uploadUtil.writeOrUpdateFile(request, value, WebConstant.EXCEL);


        try {
            UserCommand userCommand = FormUtil.populate(UserCommand.class, request);
//        userCommand.setCrudaction(WebConstant.INSERT_UPDATE);
            UserDTO pojo = userCommand.getPojo();

            if (userCommand.getUrlType() != null && userCommand.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (userCommand.getCrudaction() != null && userCommand.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {

                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(userCommand.getRoleId());
                    pojo.setRoleDTO(roleDTO);

                    if (pojo != null && pojo.getUserId() != null) {
                        SingletonServiceUtil.getUserServiceImpl().updateUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
                    } else {
                        SingletonServiceUtil.getUserServiceImpl().saveUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                    }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user/edit.jsp");
                dispatcher.forward(request, response);
            }
            if (objects != null) {
                String urlType = null;
                Map<String, String> mapValue = (Map<String, String>) objects[3];
                for (Map.Entry<String, String> item : mapValue.entrySet()) {
                    if (item.getKey().equals("urlType")) {
                        urlType = item.getValue();
                    }
                }
                if (urlType != null && urlType.equals(READ_EXCEL)) {
                    String fileLocation = (String) objects[1];
                    String fileName = (String) objects[2];
                    List<ImportUserDTO> importUserDTOS = returnValueFromExcel(fileLocation, fileName);
                    validatedData(importUserDTOS);
                    SessionUtil.getSession().putValue(request, LIST_USER_IMPORT, importUserDTOS);
                    response.sendRedirect("/toiec_web_war/admin-import-user-validated.html?urlType=validated_import");

                }
            }
            if (userCommand.getUrlType() != null && userCommand.getUrlType().equals(IMPORT_DATA)) {
                List<ImportUserDTO> importUserDTOS = (List<ImportUserDTO>) SessionUtil.getSession().getValue(request, LIST_USER_IMPORT);
                SingletonServiceUtil.getUserServiceImpl().saveImportUser(importUserDTOS);
                SessionUtil.getSession().removeValue(request, LIST_USER_IMPORT);

                response.sendRedirect("/toiec_web_war/admin-import-user-validated.html?urlType=url_list");

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
        }
    }

    private void validatedData(List<ImportUserDTO> importUserDTOS) {
        Set<String> stringSet = new HashSet<>();
        for (ImportUserDTO item : importUserDTOS) {
            requiredField(item);
            duplicateField(item, stringSet);
        }
        SingletonServiceUtil.getUserServiceImpl().validatedImportUser(importUserDTOS);

    }

    private void duplicateField(ImportUserDTO item, Set<String> stringSet) {
        String messageResponse = item.getError();
        if (!stringSet.contains(item.getUserName())) {
            stringSet.add(item.getUserName());
        } else {
            if (item.isValid()) {
                messageResponse += "<br/>";
                messageResponse += resourceBundle.getString("label.user.message.valid.duplicate.username");
            }
        }
        if (StringUtils.isNotBlank(messageResponse)) {
            item.setValid(false);
            item.setError(messageResponse);
        }
    }

    private void requiredField(ImportUserDTO item) {
        String messageResponse = "";
        if (StringUtils.isBlank(item.getUserName())) {
            messageResponse += "<br/>";
            messageResponse += resourceBundle.getString("label.user.message.valid.empty.username");
        }
        if (StringUtils.isBlank(item.getPassword())) {
            messageResponse += "<br/>";
            messageResponse += resourceBundle.getString("label.user.message.valid.empty.password");
        }
        if (StringUtils.isBlank(item.getRoleName())) {
            messageResponse += "<br/>";
            messageResponse += resourceBundle.getString("label.user.message.valid.empty.rolename");
        }
        if (StringUtils.isNotBlank(messageResponse)) {
            item.setValid(false);
        }
        item.setError(messageResponse);
    }

    private List<ImportUserDTO> returnValueFromExcel(String fileLocation, String fileName) throws IOException{
        Workbook workbook = ExcelPoiUtil.getWorkbook(fileLocation, fileName);
        Sheet sheet = workbook.getSheetAt(0);
        List<ImportUserDTO> importUserDTOS = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            ImportUserDTO importUserDTO = dataReadOnRowsExcel(row);
            importUserDTOS.add(importUserDTO);
        }
        return importUserDTOS;
    }

    private ImportUserDTO dataReadOnRowsExcel(Row row) {
        ImportUserDTO importUserDTO = new ImportUserDTO();
        importUserDTO.setUserName(ExcelPoiUtil.getCellValue(row.getCell(0)));
        importUserDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
        importUserDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(2)));
        importUserDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));
        return importUserDTO;
    }
}
