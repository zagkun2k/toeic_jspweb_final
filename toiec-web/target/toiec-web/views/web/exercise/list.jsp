<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="urlList" value="/danh-sach-bai-tap-nghe.html"/>
<%--<c:url var="listenExerciseUrl" value="bai-tap-phan-nghe.html"/>--%>
<html lang="en">
<head>
    <title><fmt:message key="label.exercise.list.listening" bundle="${lang}"/></title>
    <style>
        .wrapper{
            margin: 60px auto;
            text-align: center;
        }
        #pagination-demo{
            display: inline-block;
            margin-bottom: 1.75em;
        }
        #pagination-demo li{
            display: inline-block;
        }

        .page-content{
            background: #eee;
            display: inline-block;
            padding: 10px;
            width: 100%;
            max-width: 660px;
        }
    </style>
</head>
<body>
<form action="${urlList}" method="get" id="formUrl">
    <div class="wrap">
        <div class="main">
            <div class="content">
                <div class="col span_2_of_3">
                    <div class="contact-form">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title"><fmt:message key="label.search" bundle="${lang}"/></h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <input type="text" value="${items.pojo.name}"
                                                                   class="form-control input-sm" name="pojo.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button class="btn btn-sm btn-success">
                                                            <fmt:message key="label.search" bundle="${lang}"/>
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:forEach var="item" items="${items.listResult}">
                    <div class="image group">
                        <div class="grid news_desc">
                            <h3>${item.name}</h3>
                            <c:url var="detailUrl" value="bai-tap-phan-nghe.html">
                                <c:param name="page" value="1"/>
                                <c:param name="exerciseId" value="${item.exerciseId}" />
                            </c:url>
                                <%--                        <h5>${item.content}</h5>--%>
                            <h4><span><a href="<c:url value="${detailUrl}"/>"><fmt:message key="label.exercise.listening"
                                                                                           bundle="${lang}"/></a></span></h4>
                        </div>
                    </div>
                </c:forEach>
                <div class="wrapper">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <ul id="pagination-demo" class="pagination-sm"></ul>
                            </div>
                        </div>
                        <div id="page-content" class="page-content">Page 1</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="page" name="page"/>
    <input type="hidden" name="pojo.type" value="${item.pojo.type}"/>
</form>
<script type="text/javascript">
    var startPage = ${items.page};
    var totalPages = ${items.totalPages};
    var visiblePages = ${items.maxPageItems};

    $(document).ready(function () {});
    $(function () {
        var obj = $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            startPage: startPage,
            visiblePages: visiblePages,
            next: 'Next',
            prev: 'Prev',
            onPageClick: function (event, page) {
                //fetch content and render here
                if (page != startPage) {
                    $('#page').val(page);
                    $('#formUrl').submit();
                }
                $('#page-content').text('Page ' + page) + ' content here';
            }
        });
    });
</script>
</body>
</html>
