package com.truonggiang.web.controller;

import com.truonggiang.core.common.constant.CoreConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

//@WebServlet("/admin-home.html")
public class DisplayImage extends HttpServlet {
    private final String imagesBase = "/" + CoreConstant.UPLOAD_FILE;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageUrl = request.getRequestURI();
        String relativeImagePath = imageUrl.substring("/toiec_web_war/repository/".length());
        ServletOutputStream outputStream = response.getOutputStream();

        FileInputStream inputStream = new FileInputStream(imagesBase + File.separator + relativeImagePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        int ch = 0;
        while ( (ch = bufferedInputStream.read()) != -1 ) {
            bufferedOutputStream.write(ch);
        }

        bufferedInputStream.close();
        inputStream.close();

        bufferedOutputStream.close();
        outputStream.close();
    }
}
