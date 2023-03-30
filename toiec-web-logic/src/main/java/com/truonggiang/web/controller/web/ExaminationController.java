package com.truonggiang.web.controller.web;

import com.truonggiang.core.dto.ExaminationDTO;
import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.web.command.ExaminationCommand;
import com.truonggiang.web.command.ExerciseCommand;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/danh-sach-bai-thi-tong-hop.html"})
public class ExaminationController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExaminationCommand examinationCommand = FormUtil.populate(ExaminationCommand.class, request);
        executeSearchValue(request, examinationCommand);
        request.setAttribute(WebConstant.LIST_ITEMS, examinationCommand);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/examination/list.jsp");
        dispatcher.forward(request, response);
    }

    private void executeSearchValue(HttpServletRequest request, ExaminationCommand examinationCommand) {
        Map<String, Object> property = getMapValue(examinationCommand);
        examinationCommand.setMaxPageItems(3);
        RequestUtil.initSearchBeanUtilManual(examinationCommand);

        Object[] objects = SingletonServiceUtil.getExaminationServiceImpl().findExaminationByProperty(property, examinationCommand.getSortExpression(), examinationCommand.getSortDirection(), examinationCommand.getFirstItem(), examinationCommand.getMaxPageItems());

        examinationCommand.setListResult((List<ExaminationDTO>) objects[1]);
        examinationCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        examinationCommand.setTotalPages((int) Math.ceil((double) examinationCommand.getTotalItems() / examinationCommand.getMaxPageItems()));
    }

    private Map<String, Object> getMapValue(ExaminationCommand examinationCommand) {
        Map<String, Object> property = new HashMap<>();
        if (StringUtils.isNotBlank(examinationCommand.getPojo().getName())) {
            property.put("name", examinationCommand.getPojo().getName());
        }
        return property;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/examination/list.jsp");
        dispatcher.forward(request, response);
    }
}
