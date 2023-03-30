<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%--<c:url var="listExerciseUrl" value="/danh-sach-bai-tap-nghe.html" />--%>
<%--<c:url var="practiceExerciseUrl" value="/bai-tap-phan-nghe.html" />--%>
<c:url value="/ajax-bai-tap-dap-an.html" var="callAjax"/>
<html>
<head>
    <title><fmt:message key="label.exercise.listening" bundle="${lang}"/></title>
</head>
<body>
    <form action="" method="get" id="formUrl">
        <div class="row">
            <div class="span12">
                <ul class="thumbnails">
                    <li class="span12">
                        <div class="thumbnail" id="result">
                            <c:forEach var="item" items="${items.listResult}">
                                <p>
                                    ${item.question}
                                </p>
                                <c:if test="${item.image != null}">
                                    <p>
                                        <img src="<c:url value="/repository/${item.image}"/>" width="300px" height="150px">
                                    </p>
                                </c:if>
                                <c:if test="${item.audio != null}">
                                    <p>
                                        <audio controls>
                                            <source src="<c:url value="/repository/${item.audio}"/>" type="audio/mpeg">
                                        </audio>
                                    </p>
                                </c:if>
                                <p>
                                    <input type="radio" name="answerUser" value="A"/>
                                        ${item.option1}
                                </p>
                                <p>
                                    <input type="radio" name="answerUser" value="B"/>
                                        ${item.option2}
                                </p>
                                <p>
                                    <input type="radio" name="answerUser" value="C"/>
                                        ${item.option3}
                                </p>
                                <p>
                                    <input type="radio" name="answerUser" value="D"/>
                                        ${item.option4}
                                </p>
                                <input type="hidden" name="exerciseId" id="exerciseId" value="${item.exerciseDTO.exerciseId}" />
                            </c:forEach>
                        </div>
                    </li>
                </ul>
                <div class="wrapper">
                    <div class="container">
                        <div class="row">
                            <ul id="pagination-demo" class="pagination-sm"></ul>
                        </div>
                        <div class="row">
                            <input type="button" class="btn btn-info" value="Xem đáp án" id="btnConfirm"/>
                            <input type="button" class="btn btn-info" value="Làm lại" id="btnAgain"/>
                        </div>
                        <div class="row" />
                            <br/>
                        </div>
                        <div class="row">
                                <input type="button" class="btn btn-info" value="<fmt:message key="label.exercise.list.back" bundle="${lang}"/>" id="btnBack"/>
                                <input type="button" class="btn btn-info" value="Chuyển qua làm bài tập phần đọc" id="btnReading" />
                                <input type="button" class="btn btn-info" value="Chuyển về lại bài tập phần nghe" id="btnBackListening" disabled="disabled"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="pojo.type" id="type" />
        <input type="hidden" name="page" id="page" value="${items.page}"/>
    </form>
<script type="text/javascript">

    var startPage = ${items.page};
    var totalPages = ${items.totalPages};
    <%--var visiblePages = ${items.maxPageItems};--%>
    <%--var backFormUrl = "${listExerciseUrl}";--%>
    var callResult = '${callAjax}';
    $(document).ready(function () {
        $('#btnBack').click(function () {
            $('#type').val("listening");
            var type =  $('#type').val();
            window.location = '/toiec_web_war/danh-sach-bai-tap-nghe.html?pojo.type='+type+''
        })

        $('#btnReading').click(function () {
            window.location = '/toiec_web_war/bai-tap-phan-nghe.html?page=1&exerciseId=2';
            $('#btnReading').prop('disabled', true);
            $('#btnBackListening').prop('disabled', false);
        })

        $('#btnBackListening').click(function () {
            window.location = '/toiec_web_war/bai-tap-phan-nghe.html?page=1&exerciseId=1';
            $('#btnBackListening').prop('disabled', true);
        })

        $('#btnConfirm').click(function () {
            if ($('input[name="answerUser"]:checked').length > 0) {
                $('#formUrl').submit();
            } else {
                alert("Ban chua chon dap an nao ca!");
            }
        })

        $('#btnAgain').click(function () {
            var exerciseId = $('#exerciseId').val();
            window.location = '/toiec_web_war/bai-tap-phan-nghe.html?page='+startPage+'&exerciseId='+exerciseId+'';
        })
    })

    $(function () {
        var obj = $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            startPage: startPage,
            visiblePages: 0,
            next: 'Next',
            prev: 'Prev',
            onPageClick: function (event, page) {
                //fetch content and render here
                if (page != startPage) {
                    $('#page').val(page);
                    var exerciseId = $('#exerciseId').val();
                    window.location = '/toiec_web_war/bai-tap-phan-nghe.html?page='+page+'&exerciseId='+exerciseId+'';
                }
            }
        });
    });

    $('#formUrl').submit(function (e) {
        e.preventDefault();
        $.ajax({

        })
        $.ajax({
            type: 'POST',
            // url: '/toiec_web_war/ajax-bai-tap-dap-an.html',
            url: callResult,
            data: $(this).serialize(),
            // data: 'answerUser=A&exerciseId=1&pojo.type=listening&page=1',
            dateType: 'html',
            success: function(res){
                $('#result').html(res);
            },
            error : function (res) {
                console.log(res)
            }
        });
    })
</script>
</body>
</html>
