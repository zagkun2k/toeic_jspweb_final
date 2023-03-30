package com.truonggiang.web.controller.web;

import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
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

@WebServlet(urlPatterns = {"/danh-sach-bai-tap-nghe.html"})
public class ExerciseController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseCommand exerciseCommand = FormUtil.populate(ExerciseCommand.class, request);
        executeSearchValue(request, exerciseCommand);
        request.setAttribute(WebConstant.LIST_ITEMS, exerciseCommand);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/exercise/list.jsp");
        dispatcher.forward(request, response);
    }

    private void executeSearchValue(HttpServletRequest request, ExerciseCommand exerciseCommand) {
        Map<String, Object> property = getMapValue(exerciseCommand);
        exerciseCommand.setMaxPageItems(3);
        RequestUtil.initSearchBeanUtilManual(exerciseCommand);

        Object[] objects = SingletonServiceUtil.getExerciseServiceImpl().findExerciseByProperty(property, exerciseCommand.getSortExpression(), exerciseCommand.getSortDirection(), exerciseCommand.getFirstItem(), exerciseCommand.getMaxPageItems());

        exerciseCommand.setListResult((List<ExerciseDTO>) objects[1]);
        exerciseCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        exerciseCommand.setTotalPages((int) Math.ceil((double) exerciseCommand.getTotalItems() / exerciseCommand.getMaxPageItems()));
    }

    private Map<String, Object> getMapValue(ExerciseCommand exerciseCommand) {
        Map<String, Object> property = new HashMap<>();
        if (StringUtils.isNotBlank(exerciseCommand.getPojo().getName())) {
            property.put("name", exerciseCommand.getPojo().getName());
        }
        if (StringUtils.isNotBlank(exerciseCommand.getPojo().getType())) {
            property.put("type", exerciseCommand.getPojo().getType());
        }
        return property;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/exercise/list.jsp");
        dispatcher.forward(request, response);
    }
}
