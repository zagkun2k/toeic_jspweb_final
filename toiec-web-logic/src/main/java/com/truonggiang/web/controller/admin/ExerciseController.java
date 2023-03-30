package com.truonggiang.web.controller.admin;

import com.truonggiang.core.common.utils.UploadUtil;
import com.truonggiang.core.web.common.WebConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/admin-exercise-upload")
public class ExerciseController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/exercise/upload.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> titleValue = new HashSet<>();

        uploadUtil.writeOrUpdateFile(request, titleValue, WebConstant.EXERCISE);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/exercise/upload.jsp");
        dispatcher.forward(request, response);
    }
}
