package com.truonggiang.web.controller.admin;

import com.lowagie.text.html.HtmlParser;
import com.truonggiang.core.common.utils.UploadUtil;
import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.persistence.entity.ListenGuidelineEntity;
import com.truonggiang.core.service.ListenGuidelineService;
import com.truonggiang.core.serviceimpl.ListenGuidelineServiceImpl;
import com.truonggiang.core.utils.ListenGuidelineBeanUtil;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.core.web.utils.WebCommonUtil;
import com.truonggiang.web.command.ListenGuidelineCommand;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ListenGuidelineCommand listenGuidelineCommand = FormUtil.populate(ListenGuidelineCommand.class, request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

        if (listenGuidelineCommand.getUrlType() != null && listenGuidelineCommand.getUrlType().equals(WebConstant.URL_LIST)) {
            if (listenGuidelineCommand.getCrudaction() != null && listenGuidelineCommand.getCrudaction().equals(WebConstant.REDIRECT_DELETE)) {
                List<Integer> ids = new ArrayList<>();
                for (String item : listenGuidelineCommand.getCheckList()) {
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getListenGuidelineServiceImpl().deleteListenGuideline(ids);
                if (result != ids.size()) {
                    listenGuidelineCommand.setCrudaction(WebConstant.NOTHING_MESSAGE);
                }
            }
            executeSearchValue(request, listenGuidelineCommand);
            if (listenGuidelineCommand.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapMessageResponse(resourceBundle);
                WebCommonUtil.addRedirectMessage(request, listenGuidelineCommand.getCrudaction(), mapMessage);
            }
            request.setAttribute(WebConstant.LIST_ITEMS, listenGuidelineCommand);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            dispatcher.forward(request, response);
        } else if (listenGuidelineCommand.getUrlType() != null && listenGuidelineCommand.getUrlType().equals(WebConstant.URL_EDIT)) {
            if (listenGuidelineCommand.getPojo() != null && listenGuidelineCommand.getPojo().getListenGuidelineId() != null) {
                listenGuidelineCommand.setPojo(SingletonServiceUtil.getListenGuidelineServiceImpl().findEniqueValue("listenGuidelineId", listenGuidelineCommand.getPojo().getListenGuidelineId()));
            }
            request.setAttribute(WebConstant.FORM_ITEM, listenGuidelineCommand);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            dispatcher.forward(request, response);
        }

//        session.removeAttribute(WebConstant.ALERT);
//        session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
    }

    private Map<String, String> buildMapMessageResponse(ResourceBundle resourceBundle) {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.listenguideline.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.listenguideline.message.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.listenguideline.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        mapMessage.put(WebConstant.NOTHING_MESSAGE, resourceBundle.getString("label.message.nothing"));
        return mapMessage;
    }

    private void executeSearchValue(HttpServletRequest request, ListenGuidelineCommand listenGuidelineCommand) {
        RequestUtil.initSearchBeanUtil(request, listenGuidelineCommand);
        Map<String, Object> property = getMapValue(listenGuidelineCommand);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceImpl().findListenGuidelineByProperty(property, listenGuidelineCommand.getSortExpression(), listenGuidelineCommand.getSortDirection(), listenGuidelineCommand.getFirstItem(), listenGuidelineCommand.getMaxPageItems());
        listenGuidelineCommand.setListResult((List<ListenGuidelineDTO>) objects[1]);
        listenGuidelineCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> getMapValue(ListenGuidelineCommand listenGuidelineCommand) {
        Map<String, Object> property = new HashMap<>();
        if (StringUtils.isNotBlank(listenGuidelineCommand.getPojo().getTitle())) {
            property.put("title", listenGuidelineCommand.getPojo().getTitle());
        }
        return property;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ListenGuidelineCommand listenGuidelineCommand = new ListenGuidelineCommand();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

//        HttpSession session = request.getSession();
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> titleValue = valueSetListenGuideline();
        Object[] objects = uploadUtil.writeOrUpdateFile(request, titleValue, WebConstant.LISTENGUIDELINE);
        boolean checkUploadStatus = (boolean) objects[0];
        if (!checkUploadStatus) {
            //loi upload
            response.sendRedirect("/toiec_web_war/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
        } else {
            ListenGuidelineDTO dto = listenGuidelineCommand.getPojo();
            if (StringUtils.isNotBlank(objects[2].toString())) {
                dto.setImage(objects[2].toString());
            }
            Map<String, String> map = (Map<String, String>) objects[3];
            dto = getDtoValue(dto, map);
            //thuc hien upload
            if (dto != null) {
                if (dto.getListenGuidelineId() != null) {
                    //update
                    ListenGuidelineDTO findValue = SingletonServiceUtil.getListenGuidelineServiceImpl().findEniqueValue("listenGuidelineId", dto.getListenGuidelineId());
                    if (dto.getImage() == null) {
                        dto.setImage(findValue.getImage());
                    }
                    dto.setCreatedDate(findValue.getCreatedDate());

                    ListenGuidelineDTO resultUpdate =  SingletonServiceUtil.getListenGuidelineServiceImpl().updateListenGuideline(dto);
                    if (resultUpdate != null) {
                        response.sendRedirect("/toiec_web_war/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_update");
                    } else {
                        response.sendRedirect("/toiec_web_war/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
                    }

                } else {
                    //insert
                    try {
                        SingletonServiceUtil.getListenGuidelineServiceImpl().saveListenGuideline(dto);
                        response.sendRedirect("/toiec_web_war/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_insert");
                    } catch (RuntimeException e) {
                        response.sendRedirect("/toiec_web_war/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
            }
        }


//        Map<String, String> mapValue = (Map<String, String>) objects[3];
//        listenGuidelineCommand = returnValueListenGuidelineCommand(titleValue, listenGuidelineCommand, mapValue);
//        response.sendRedirect("/toiec_web_war/admin-guideline-listen-list.html?urlType=url_list");
    }

    private ListenGuidelineDTO getDtoValue(ListenGuidelineDTO listenGuidelineDTO, Map<String, String> mapValue) {
        for (Map.Entry<String, String> item : mapValue.entrySet()) {
            if (item.getKey().equals("pojo.title")) {
                listenGuidelineDTO.setTitle(item.getValue());
            } else if (item.getKey().equals("pojo.content")) {
                listenGuidelineDTO.setContent(item.getValue());
            } else if (item.getKey().equals("pojo.listenGuidelineId")) {
                listenGuidelineDTO.setListenGuidelineId(Integer.parseInt(item.getValue().toString()));
            }
        }
        return listenGuidelineDTO;
    }

    private Set<String> valueSetListenGuideline() {
        Set<String> value = new HashSet<>();
        value.add("pojo.title");
        value.add("pojo.content");
        value.add("pojo.listenGuidelineId");
        return value;
    }
}
