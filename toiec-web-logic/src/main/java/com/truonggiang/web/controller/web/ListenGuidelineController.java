package com.truonggiang.web.controller.web;

import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.persistence.entity.ListenGuidelineEntity;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.web.command.ListenGuidelineCommand;
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

@WebServlet(urlPatterns = {"/danh-sach-huong-dan-nghe.html", "/danh-sach-noi-dung-bai-huong-dan-nghe.html"})
public class ListenGuidelineController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListenGuidelineCommand listenGuidelineCommand = FormUtil.populate(ListenGuidelineCommand.class, request);
        if (request.getParameter("listenguidelineid") != null) {

            String listenGuidlineIdString = request.getParameter("listenguidelineid");
            ListenGuidelineDTO existListenGuidline = SingletonServiceUtil.getListenGuidelineServiceImpl().findEniqueValue("listenGuidelineId", Integer.parseInt(listenGuidlineIdString));
            listenGuidelineCommand.setPojo(existListenGuidline);

            request.setAttribute(WebConstant.FORM_ITEM, listenGuidelineCommand);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/listenguideline/detail.jsp");
            dispatcher.forward(request, response);
        } else {
            executeSearchValue(request, listenGuidelineCommand);
            request.setAttribute(WebConstant.LIST_ITEMS, listenGuidelineCommand);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/listenguideline/list.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void executeSearchValue(HttpServletRequest request, ListenGuidelineCommand listenGuidelineCommand) {
        Map<String, Object> property = getMapValue(listenGuidelineCommand);
        listenGuidelineCommand.setMaxPageItems(3);
        RequestUtil.initSearchBeanUtilManual(listenGuidelineCommand);

        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceImpl().findListenGuidelineByProperty(property, listenGuidelineCommand.getSortExpression(), listenGuidelineCommand.getSortDirection(), listenGuidelineCommand.getFirstItem(), listenGuidelineCommand.getMaxPageItems());

        listenGuidelineCommand.setListResult((List<ListenGuidelineDTO>) objects[1]);
        listenGuidelineCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        listenGuidelineCommand.setTotalPages((int) Math.ceil((double) listenGuidelineCommand.getTotalItems() / listenGuidelineCommand.getMaxPageItems()));
    }

    private Map<String, Object> getMapValue(ListenGuidelineCommand listenGuidelineCommand) {
        Map<String, Object> property = new HashMap<>();
        if (StringUtils.isNotBlank(listenGuidelineCommand.getPojo().getTitle())) {
            property.put("title", listenGuidelineCommand.getPojo().getTitle());
        }
        return property;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/listenguideline/list.jsp");
        dispatcher.forward(request, response);
    }
}
