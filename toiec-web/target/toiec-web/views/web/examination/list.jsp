<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%--<c:url var="examUrl" value="/bai-thi-tong-hop.html"></c:url>--%>
<c:url var="listExamUrl" value="/danh-sach-bai-thi-tong-hop.html"></c:url>
<html lang="en">
<head>
    <title><fmt:message key="label.exam.list" bundle="${lang}"/></title>
</head>
<body>
<form action="${listExamUrl}" method="get" id="formUrl">
    <div class="wrap">
        <div class="main">
            <div class="content">
                <div class="col span_2_of_3">
                    <div class="contact-form">
                        <div>
                        <span>
                            <input name="pojo.name" type="text" class="textbox" value="${items.pojo.name}"/>
                        </span>
                        </div>
                        <div>
                            <button class="btn btn-sm btn-success">
                                <fmt:message key="label.search" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </div>
                <c:forEach var="item" items="${items.listResult}">
                    <div class="image group">
                        <div class="grid news_desc">
                            <h3>${item.name}</h3>
                            <c:url var="examUrl" value="bai-thi-tong-hop.html">
<%--                                <c:param name="page" value="1" />--%>
                                <c:param name="examinationId" value="${item.examinationId}" />
                            </c:url>
                            <c:if test="${not empty login_name}">
                                <h4><span><a href="<c:url value="${examUrl}"/>"><fmt:message key="label.do.exam"
                                                                                             bundle="${lang}"/></a></span></h4>
                            </c:if>
                            <c:if test="${empty login_name}">
                                <c:url var="loginUrl" value="login.html">
                                    <c:param name="action" value="login"/>
                                </c:url>
                                <h4><span><a onclick="checkLogin()" href="<c:url value="${loginUrl}"/>"><fmt:message key="label.do.exam" bundle="${lang}"/></a></span></h4>
                            </c:if>

<%--                            <h4><span><fmt:message key="label.examination.require" bundle="${lang}"/></span></h4>--%>
                        </div>
                    </div>
                </c:forEach>
                <ul id="pagination-demo" class="pagination-sm"></ul>
            </div>
        </div>
    </div>
    <input type="hidden" id="page" name="page"/>
</form>
<script type="text/javascript">
    var totalPages = ${items.totalPages};
    var startPage = ${items.page};
    var visiblePages = ${items.maxPageItems};
    var loginName = '${login_name}';
    $(document).ready(function () {
    });
    $(function () {
        var obj = $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            startPage: startPage,
            visiblePages: visiblePages,
            onPageClick: function (event, page) {
                if (page != startPage) {
                    $('#page').val(page);
                    $('#formUrl').submit();
                }
            }
        });
    });
    function checkLogin() {
        if (loginName === '') {
            alert('<fmt:message key="label.examination.require" bundle="${lang}"/>')
        }
    }
</script>
</body>
</html>