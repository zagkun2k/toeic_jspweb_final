<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="homeUrl" value="/home.html"/>
<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <div class="active item">
            <div class="container">
                <div class="row">
                    <div class="span6">
                        <div class="carousel-caption">
                            <h1>LeTOEIC English</h1>
                            <p class="lead">
                                Improve your skill
                            </p>
                            <a class="btn btn-large btn-primary" href="${homeUrl}"
                            >Đăng kí để được tư vấn</a
                            >
                        </div>
                    </div>

                    <div class="span6"><img src="<c:url value="/template/web/img/topic1.jpg"/>" /></div>
                </div>
            </div>
        </div>

        <div class="item">
            <div class="container">
                <div class="row">
                    <div class="span6">
                        <div class="carousel-caption">
                            <h1>LeTOEIC English</h1>
                            <p class="lead">
                                The best choice for your English Partner
                            </p>
                            <a class="btn btn-large btn-primary" href="${homeUrl}"
                            >Đăng kí để được tư vấn</a
                            >
                        </div>
                    </div>

                    <div class="span6"><img src="<c:url value="/template/web/img/topic3.jpg"/>" /></div>
                </div>
            </div>
        </div>
    </div>
    <!-- Carousel nav -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev"
    ><i class="icon-chevron-left"></i
    ></a>
    <a class="carousel-control right" href="#myCarousel" data-slide="next"
    ><i class="icon-chevron-right"></i
    ></a>
    <!-- /.Carousel nav -->
</div>
<!-- /Carousel -->

<!-- Feature
==============================================-->

<div class="row feature-box">
    <div class="span12 cnt-title">
        <h1>LeTOEIC English</h1>
        <span>
            Class Room
        </span
        >
    </div>

    <div class="span4">
        <img src="<c:url value="/template/web/img/guide.png"/>" width="225px" height="225px" />
        <h2><fmt:message key="label.tutorial" bundle="${lang}" /> </h2>
        <br/>
        <br/>
        <a href="<c:url value="/danh-sach-huong-dan-nghe.html"/>">Vào phòng &rarr;</a>
    </div>

    <div class="span4">
        <img src="<c:url value="/template/web/img/listening.jpg"/>" width="225px" height="225px" />
        <h2><fmt:message key="label.listenning.test" bundle="${lang}" /> </h2>
        <br/>
        <br/>
        <c:url var="listExerciseListenUrl" value="/danh-sach-bai-tap-nghe.html">
            <c:param name="pojo.type" value="listening" />
        </c:url>
        <a href="${listExerciseListenUrl}">Vào phòng &rarr;</a>
    </div>

    <div class="span4">
        <br/>
        <img src="<c:url value="/template/web/img/exam_1.png"/>" width="180px" height="185px" style="height: 185px;" />
        <br/>
        <br/>
        <h2><fmt:message key="label.exam" bundle="${lang}" /> </h2>
        <br/>
        <br/>
        <a href="<c:url value="/danh-sach-bai-thi-tong-hop.html"/>">Vào phòng &rarr;</a>
    </div>
</div>

<!-- /.Feature -->

<div class="hr-divider"></div>

<!-- Row View -->

<div class="row">
    <div class="span6"><img src="<c:url value="/template/web/img/topic4.jpg"/>" width="570px" height="286px"/></div>

    <div class="span6">
        <img class="hidden-phone" src="<c:url value="/template/web/img/topic2.jpg"/>" alt="" width="128px" height="128px"/>
        <h1>LeTOEIC English</h1>
        <br/>
        <a href="${homeUrl}">Liên hệ với chúng tôi &rarr;</a>
    </div>
</div>
</div>

<!-- /.Row View -->