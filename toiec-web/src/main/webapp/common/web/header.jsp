<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="homeUrl" value="/home.html"/>
<div id="header-row">
    <div class="container">
        <div class="row">
            <!--LOGO-->
            <div class="span3">
                <a class="brand" href="${homeUrl}"><img src="<c:url value="/template/web/img/banner_web.png"/>" width="140px" height="50px" style="height: 50px;"/></a>
            </div>
            <!-- /LOGO -->

            <!-- MAIN NAVIGATION -->
            <div class="span9">
                <div class="navbar pull-right">
                    <div class="navbar-inner">
                        <a
                                data-target=".navbar-responsive-collapse"
                                data-toggle="collapse"
                                class="btn btn-navbar"
                        ><span class="icon-bar"></span><span class="icon-bar"></span
                        ><span class="icon-bar"></span
                        ></a>
                        <div class="nav-collapse collapse navbar-responsive-collapse">
                            <ul class="nav">
<%--                                <li class="active"><a href="index.html">Trang chủ</a></li>--%>

                                <%--<li class="dropdown">
                                    <a
                                            href="about.html"
                                            class="dropdown-toggle"
                                            data-toggle="dropdown"
                                    >Chi tiết <b class="caret"></b
                                    ></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="about.html">Về công ty</a></li>
                                        <li><a href="about.html">Lịch sử</a></li>
                                        <li><a href="about.html">Về Team</a></li>
                                    </ul>
                                </li>

                                <li><a href="service.html">Dịch vụ</a></li>--%>
                                <c:if test="${not empty login_name}">
                                    <ul class="nav ace-nav">
                                        <li class="light-blue dropdown-modal">
                                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                                Welcome, ${login_name}

                                            </a>
                                        <li class="light-blue dropdown-modal">
                                            <c:url var="logoutUrl" value="/logout.html">
                                                <c:param name="action" value="logout"/>
                                            </c:url>
                                            <a href="${logoutUrl}">
                                                <i class="ace-icon fa fa-power-off"></i>
                                                <fmt:message key="label.logout" bundle="${lang}"/>
                                            </a>
                                        </li>
                                        </li>
                                    </ul>
                                </c:if>
                                <%--<c:if test="${not empty login_name}">
                                    <div class="navbar-buttons navbar-header pull-right collapse navbar-collapse" role="navigation">
                                        <ul class="nav ace-nav">
                                            <li class="light-blue dropdown-modal">
                                                <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                                    Welcome, ${login_name}

                                                </a>
                                            <li class="light-blue dropdown-modal">
                                                <c:url var="logoutUrl" value="/logout.html">
                                                    <c:param name="action" value="logout"/>
                                                </c:url>
                                                <a href="${logoutUrl}">
                                                    <i class="ace-icon fa fa-power-off"></i>
                                                    <fmt:message key="label.logout" bundle="${lang}"/>
                                                </a>
                                            </li>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>--%>
                                <c:if test="${empty login_name}">
                                    <c:url var="loginUrl" value="/login.html">
                                        <c:param name="action" value="login"/>
                                    </c:url>
                                    <a href="${loginUrl}">
                                        <i class="ace-icon fa fa-power-off"></i>
                                        <fmt:message key="label.login" bundle="${lang}"/>
                                    </a>
                                </c:if>
                                <%--<c:if test="${empty login_name}">
                                    <div class="navbar-buttons navbar-header pull-right collapse navbar-collapse" role="navigation">
                                        <ul class="nav ace-nav">
                                            <li class="light-blue dropdown-modal">
                                                <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                                    Đăng kí
                                                </a>
                                            <li class="light-blue dropdown-modal">
                                                <c:url var="logoutUrl" value="/logout.html">
                                                    <c:param name="action" value="login"/>
                                                </c:url>
                                                <a href="${logoutUrl}">
                                                    <i class="ace-icon fa fa-power-off"></i>
                                                    <fmt:message key="label.login" bundle="${lang}"/>
                                                </a>
                                            </li>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>--%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- MAIN NAVIGATION -->
        </div>
    </div>
</div>
<!-- /HEADER ROW -->