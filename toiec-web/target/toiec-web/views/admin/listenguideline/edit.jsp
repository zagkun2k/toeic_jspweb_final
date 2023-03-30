<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin-guideline-listen-edit.html"/>
<%--<c:url var="listUrl" value="/admin-guideline-listen-list.html"/>--%>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
    <style>
        .error {
            color: red;
        }

        .input-text {
            color: red;
            background-color: #ff57ba;
        }
    </style>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li><fmt:message key="label.guideline.listen.list" bundle="${lang}"/></li>
                <li class="active"><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>

                    <form action="${formUrl}" method="post" enctype="multipart/form-data" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.guideline.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.title" id="title" value="${item.pojo.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.grammarguideline.upload.image" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.listenguideline.image.review" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.image}">
                                    <c:set var="image" value="/toiec_web_war/repository/${item.pojo.image}"/>
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150px">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message
                                    key="label.guideline.content" bundle="${lang}"/></label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.content}">
                                    <c:set var="content" value="${item.pojo.content}" />
                                </c:if>
                                <textarea name="pojo.content" cols="80" rows="10" id="ListenGuideLineEditContent">${content}</textarea>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold"
                                       value="<fmt:message key="label.done" bundle="${lang}"/>"/>
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.listenGuidelineId}">
                            <input type="hidden" name="pojo.listenGuidelineId" value="${item.pojo.listenGuidelineId}" />
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    var listenGuidelineId = '';
    <c:if test="${not empty item.pojo.listenGuidelineId}">
        listenGuidelineId = ${item.pojo.listenGuidelineId};
    </c:if>
    $(document).ready(function () {
        // CKEDITOR.replace('listenGuideLineEditContent');
        var editor = CKEDITOR.replace('ListenGuideLineEditContent');
        CKFinder.setupCKEditor( editor, '/toiec_web_war/ckfinder/');
        validateData();
        $('#uploadImage').change(function () {
            readImageFileUpload(this, 'viewImage');
        })
    });

    function validateData() {
        $('#formEdit').validate({
            ignore: [],
            rules: [],
            messages: []
        })
        $('#title').rules("add", {
            required: true,
            minlength: 5,
            messages: {
                required: jQuery.validator.format("<fmt:message key="label.listenguideline.validate.requiredField" bundle="${lang}"/>"),
                minlength: jQuery.validator.format("<fmt:message key="label.listenguideline.validate.minlength" bundle="${lang}"/>")
            }
        })
        if (listenGuidelineId === null) {
            $('#uploadImage').rules("add", {
                required: true,
                messages: {
                    required: jQuery.validator.format("<fmt:message key="label.listenguideline.validate.requiredField" bundle="${lang}"/>"),
                }
            })
        }
        $('#ListenGuideLineEditContent').rules("add", {
            required: function () {
                CKEDITOR.instances.ListenGuideLineEditContent.updateElement();
            },
            messages: {
                required: "<fmt:message key="label.listenguideline.validate.requiredField" bundle="${lang}"/>",
            }
        })
    }

    function readImageFileUpload(input, imageId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
