<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<html>
    <head>
        <title><fmt:message key="label.web" bundle="${lang}"/></title>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <!-- Bootstrap -->
        <link href="<c:url value="/template/web/css/bootstrap.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/template/web/css/bootstrap-responsive.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/template/web/css/style.css"/>" rel="stylesheet"/>

<%--        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">--%>
<%--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--%>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

        <!--Font-->
<%--        <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />" />--%>

<%--        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>--%>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
        <![endif]-->

        <!-- Fav and touch icons -->

        <!-- SCRIPT
        ============================================================-->
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <dec:head/>
    </head>
    <body>
        <%@ include file="/common/web/header.jsp" %>
        <div class="container">
            <dec:body/>
        </div>
        <%@ include file="/common/web/footer.jsp" %>

    </body>
</html>
