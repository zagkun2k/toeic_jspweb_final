package com.truonggiang.web.controller.web;

import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.dto.ExerciseQuestionDTO;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.web.command.ExerciseCommand;
import com.truonggiang.web.command.ExerciseQuestionCommand;

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

@WebServlet(urlPatterns = {"/bai-tap-phan-nghe.html", "/ajax-bai-tap-dap-an.html"})
public class ExerciseQuestionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseQuestionCommand exerciseQuestionCommand = FormUtil.populate(ExerciseQuestionCommand.class, request);
        getListExerciseQuestion(exerciseQuestionCommand);
        request.setAttribute(WebConstant.LIST_ITEMS, exerciseQuestionCommand);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/exercise/detail.jsp");
        dispatcher.forward(request, response);
    }

    private void getListExerciseQuestion(ExerciseQuestionCommand exerciseQuestionCommand) {
        exerciseQuestionCommand.setMaxPageItems(1);
        RequestUtil.initSearchBeanUtilManual(exerciseQuestionCommand);
//        exerciseQuestionCommand.setFirstItem(1);

        Object[] objects = SingletonServiceUtil.getExerciseQuestionServiceImpl().findExerciseQuestionByProperty(new HashMap<String, Object>(), exerciseQuestionCommand.getSortExpression(), exerciseQuestionCommand.getSortDirection(), exerciseQuestionCommand.getFirstItem(), exerciseQuestionCommand.getMaxPageItems(), exerciseQuestionCommand.getExerciseId());

        exerciseQuestionCommand.setListResult((List<ExerciseQuestionDTO>) objects[1]);
        exerciseQuestionCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        exerciseQuestionCommand.setTotalPages((int) Math.ceil((double) exerciseQuestionCommand.getTotalItems() / exerciseQuestionCommand.getMaxPageItems()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseQuestionCommand exerciseQuestionCommand = FormUtil.populate(ExerciseQuestionCommand.class, request);
        getListExerciseQuestion(exerciseQuestionCommand);

        for (ExerciseQuestionDTO item : exerciseQuestionCommand.getListResult()) {
            if (!exerciseQuestionCommand.getAnswerUser().equals(item.getCorrectAnswer())) {
                exerciseQuestionCommand.setCheckAnswer(true);
            }
        }

        request.setAttribute(WebConstant.LIST_ITEMS, exerciseQuestionCommand);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/exercise/result.jsp");
        dispatcher.forward(request, response);
    }
}
