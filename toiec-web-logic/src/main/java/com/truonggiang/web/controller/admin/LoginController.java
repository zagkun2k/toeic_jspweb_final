package com.truonggiang.web.controller.admin;

import com.truonggiang.core.common.utils.SessionUtil;
import com.truonggiang.core.dto.CheckLoginDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.service.UserService;
import com.truonggiang.core.serviceimpl.UserServiceImpl;
import com.truonggiang.core.utils.SingletonDaoUtil;
import com.truonggiang.core.web.common.WebConstant;
import com.truonggiang.core.web.utils.FormUtil;
import com.truonggiang.core.web.utils.SingletonServiceUtil;
import com.truonggiang.web.command.UserCommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/login.html", "/logout.html"})
public class LoginController extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals(WebConstant.LOGIN)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/login.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals(WebConstant.LOGOUT)) {
            SessionUtil.getSession().removeValue(request, WebConstant.LOGIN_NAME);
            response.sendRedirect("/toiec_web_war/home.html");
        } else if (action.equals(WebConstant.LOGOUT_ADMIN)) {
            SessionUtil.getSession().removeValue(request, WebConstant.LOGIN_NAME);
            response.sendRedirect("/toiec_web_war/login.html?action=login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand userCommand = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = userCommand.getPojo();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

        if (pojo != null) {
            CheckLoginDTO login = SingletonServiceUtil.getUserServiceImpl().checkLogin(pojo.getName(), pojo.getPassword());

            if (login.isUserExist()) {
                SessionUtil.getSession().putValue(request, WebConstant.LOGIN_NAME, pojo.getName());
                if (login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
                    response.sendRedirect("/toiec_web_war/admin-home.html");
                } else if (login.getRoleName().equals(WebConstant.ROLE_USER)) {
                    response.sendRedirect("/toiec_web_war/home.html");
                }
            } else {
                request.setAttribute(WebConstant.ALERT, WebConstant.DANGER);
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.message.login.error"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/web/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
