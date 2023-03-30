package com.truonggiang.web.controller.web;

import com.truonggiang.core.common.utils.SessionUtil;
import com.truonggiang.core.dto.ExaminationDTO;
import com.truonggiang.core.dto.ExaminationQuestionDTO;
import com.truonggiang.core.dto.ExerciseQuestionDTO;
import com.truonggiang.core.dto.ResultDTO;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.RequestUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.web.command.ExaminationQuestionCommand;
import com.truonggiang.web.command.ExerciseQuestionCommand;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/bai-thi-tong-hop.html", "/bai-thi-ket-qua.html"})
public class ExaminationQuestionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExaminationQuestionCommand examinationQuestionCommand = FormUtil.populate(ExaminationQuestionCommand.class, request);
        getExaminationQuestion(examinationQuestionCommand);
        request.setAttribute(WebConstant.LIST_ITEMS, examinationQuestionCommand);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/examination/detail.jsp");
        dispatcher.forward(request, response);
    }

    private void getExaminationQuestion(ExaminationQuestionCommand examinationQuestionCommand) {
//        examinationQuestionCommand.setMaxPageItems(1);
//        exerciseQuestionCommand.setFirstItem(1);

        RequestUtil.initSearchBeanUtilManual(examinationQuestionCommand);
        Object[] objects = SingletonServiceUtil.getExaminationQuestionServiceImpl().findExaminationQuestionByProperties(new HashMap<String, Object>(), examinationQuestionCommand.getSortExpression(), examinationQuestionCommand.getSortDirection(), null, null, examinationQuestionCommand.getExaminationId());

        examinationQuestionCommand.setListResult((List<ExaminationQuestionDTO>) objects[1]);
        examinationQuestionCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        examinationQuestionCommand.setTotalPages((int) Math.ceil((double) examinationQuestionCommand.getTotalItems() / examinationQuestionCommand.getMaxPageItems()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExaminationQuestionCommand examinationQuestionCommand = new ExaminationQuestionCommand();

        Integer examinationId = Integer.parseInt(request.getParameter("examinationId"));
        examinationQuestionCommand.setExaminationId(examinationId);
        getExaminationQuestion(examinationQuestionCommand);

        for (ExaminationQuestionDTO item: examinationQuestionCommand.getListResult()) {
            if (request.getParameter("answerUser["+item.getExaminationQuestionId()+"]") != null) {
                item.setAnswerUser(request.getParameter("answerUser["+item.getExaminationQuestionId()+"]"));
            }
        }

        String userName = (String) SessionUtil.getSession().getValue(request, WebConstant.LOGIN_NAME);
        ResultDTO resultDTO = SingletonServiceUtil.getResultServiceImpl().saveResult(userName, examinationId, examinationQuestionCommand.getListResult());
        examinationQuestionCommand.setReadingScore(resultDTO.getReadingScore());
        examinationQuestionCommand.setListenScore(resultDTO.getListenScore());

        request.setAttribute(WebConstant.LIST_ITEMS, examinationQuestionCommand);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/examination/result.jsp");
        dispatcher.forward(request, response);
    }
}
