<%@ page import="com.truonggiang.core.web.common.WebConstant" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:set var="PHOTO" value="<%=WebConstant.PHOTO%>"/>
<c:set var="QUESTION_RESPONSE" value="<%=WebConstant.QUESTION_RESPONSE%>"/>
<c:set var="SINGLE_PASSAGE" value="<%=WebConstant.SINGLE_PASSAGE%>"/>
<c:url var="resultUrl" value="/bai-thi-ket-qua.html"></c:url>
<html>
<head>
    <title><fmt:message key="label.exam.detail" bundle="${lang}"/></title>
    <script src="<c:url value="/template/countdown/countdown.js"/>" type="text/javascript"></script>
</head>
<body>
<form action="${resultUrl}" method="post" id="formUrl">
    <!-- START COUNTDOWN -->
    <script type="text/javascript">
        function doneHandler(result) {
            // var year = result.getFullYear();
            // var month = result.getMonth() + 1; // bump by 1 for humans
            // var day = result.getDate();
            var h = result.getHours();
            var m = result.getMinutes();
            var s = result.getSeconds();

            var UTC = result.toString();

            var output = UTC + "\n";
            // output += "year: " + year + "\n";
            // output += "month: " + month + "\n";
            // output += "day: " + day + "\n";
            output += "h: " + h + "\n";
            output += "m: " + m + "\n";
            output += "s: " + s + "\n";
        }
        var myCountdownTest = new Countdown({
            time: 60,
            width	: 300,
            height	: 50,
            onComplete : doneHandler
        });
    </script>
    <!-- END COUNTDOWN -->
    <div class="image group">
        <div class="grid news_desc">
            <div style="overflow: auto; height: 500px">
                <c:forEach var="item" items="${items.listResult}">
                    <c:if test="${item.number != null}">
                        <b>${item.number}.${item.question}</b>
                    </c:if>
                    <c:if test="${item.number == null}">
                        <b>${item.question}</b>
                    </c:if>
                    <c:choose>
                        <c:when test="${item.type == PHOTO}">
                            <p>
                                <img src="<c:url value="/repository/${item.image}"/>" width="300px" height="150px">
                            </p>
                            <p>
                                <audio controls>
                                    <source src="<c:url value="/repository/${item.audio}"/>" type="audio/mpeg">
                                </audio>
                            </p>
                        </c:when>
                        <c:when test="${item.type == QUESTION_RESPONSE}">
                            <p>
                                <audio controls>
                                    <source src="<c:url value="/repository/${item.audio}"/>" type="audio/mpeg">
                                </audio>
                            </p>
                        </c:when>
                        <c:when test="${item.type == SINGLE_PASSAGE}">
                            <p>
                                    ${item.paragraph}
                            </p>
                            <c:if test="${empty item.paragraph}">
                                <p>
                                        ${item.option1}
                                </p>
                                <p>
                                        ${item.option2}
                                </p>
                                <p>
                                        ${item.option3}
                                </p>
                                <p>
                                        ${item.option4}
                                </p>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
<%--                    <input type="hidden" name="examinationId" id="examinationId" value="${item.examinationDTO.examinationId}" />--%>
                </c:forEach>
            </div>
        </div>
        <div class="grid images_3_of_1">
            <div style="overflow: auto; height: 500px">
                <c:forEach var="item" items="${items.listResult}">
                    <c:if test="${not empty item.correctAnswer}">
                        ${item.number}.&nbsp;&nbsp;&nbsp;
                        A <input type="radio" name="answerUser[${item.examinationQuestionId}]" value="A">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        B <input type="radio" name="answerUser[${item.examinationQuestionId}]" value="B">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        C <input type="radio" name="answerUser[${item.examinationQuestionId}]" value="C">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        D <input type="radio" name="answerUser[${item.examinationQuestionId}]" value="D">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <br/>
                        <br/>
                    </c:if>
                </c:forEach>
            </div>
            <input type="hidden" name="examinationId" value="${items.examinationId}"/>
            <input type="submit" class="btn btn-primary" value="Nộp bài"/>
<%--            <a href="${resultUrl}" >Xem ket qua</a>--%>
        </div>
    </div>
</form>
<script>
    $(document).ready(function () {

    });
    $(function(){
        setTimeout(function(){
            $('#formUrl').submit();
        },60000);
    });
</script>
</body>
</html>
