<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><dec:title default="Admin Page" /></title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"  type='text/css'>
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css' />" class="ace-main-stylesheet" id="main-ace-style" />
    <script src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/template/admin/css/global_admin_style.css"/>">
    <script type="text/javascript" src="<c:url value="/template/admin/js/global_admin_script.js"/>"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

<%--    &lt;%&ndash;ckeditor plugin&ndash;%&gt;--%>
    <script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js"/>"></script>

    <%--sweetalert--%>
    <title>How to use and customize SweetAlert2</title>
    <!-- SweetAlert2 -->
<%--    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>--%>
<%--    <script src="sweetalert2.min.js"></script>--%>
<%--    <link rel="stylesheet" href="sweetalert2.min.css">--%>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css" integrity="sha512-fRVSQp1g2M/EqDBL+UFSams+aw2qk12Pl/REApotuUVK1qEXERk3nrCFChouag/PdDsPk387HJuetJ1HBx8qAg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" integrity="sha512-gOQQLjHRpD3/SEOtalVq50iDn4opLVup2TF8c4QPI3/NmUPNZOk2FG0ihi8oCU/qYEsw4P6nuEZT2lAG0UNYaw==" crossorigin="anonymous" referrerpolicy="no-referrer" />



    <%--datatable--%>
    <%--<link href="<c:url value="/template/data-table/media/css/dataTables.bootstrap4_v4.6.1.min.css"/>" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/template/data-table/data_table-1.0.0.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/template/data-table/data-table-bootstrap4-1.0.0.js"/>"></script>--%>
    <%--ckfinder--%>
    <script type="text/javascript" src="<c:url value="/ckfinder/ckfinder.js"/>"></script>
    <dec:head />
</head>
<body class="no-skin">

<!-- Header -->
<%@ include file="/common/admin/header.jsp" %>
<!-- End Header -->

<div class="main-container ace-save-state" id="main-container">

    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>

    <!-- Begin menu -->
    <%@ include file="/common/admin/menu.jsp" %>
    <!-- End menu -->


    <!-- Begin body -->
    <dec:body/>
    <!-- End body -->

    <!-- Begin footer -->
    <%@ include file="/common/admin/footer.jsp" %>
    <!-- end footer -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>

</div>
<!-- /.main-container -->

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace.min.js' />"></script>

<!-- page specific plugin scripts -->
<script src="<c:url value='/template/admin/assets/js/jquery-ui.min.js'/>"></script>

<script type="text/javascript">
    function showAlert(callback) {
        return new swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn xóa thông tin đã chọn ?",
            // type: "warning",
            icon: "warning",
            buttons: true,
            dangerMode: true,
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
            confirmButtonClass: "btn_deleteDic",
            // cancelButtonClass: "btn btn-danger"
        }).then((isConfirm) => {
                if (isConfirm) {
                    callback();
                    return new swal("Bạn đã xóa thành công!", {
                        icon: "success",
                    });
                } else {
                    return new swal("Dữ liệu của bạn vẫn được bảo toàn!", {
                        icon: "info"
                    });
                }
            });
    }
</script>
</body>
</html>