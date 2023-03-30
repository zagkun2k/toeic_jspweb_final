<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title><fmt:message key="label.home" bundle="${lang}"/></title>
</head>
<body>
<div class="row">
    <div class="span9">
        <!--Blog Post-->
        <div class="blog-post">
            <h2>${item.pojo.title}</h2>
            <p>${item.pojo.content}</p>
            <a class="btn btn-primary" href="<c:url value="/danh-sach-huong-dan-nghe.html"/>"><fmt:message key="label.list.back" bundle="${lang}"/></a>
        </div>
        <!--===============-->
    </div>
</div>
</body>
</html>
