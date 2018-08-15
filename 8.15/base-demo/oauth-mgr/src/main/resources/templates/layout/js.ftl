<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${cpath}/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${cpath}/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${cpath}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${cpath}/assets/js/bootstrap.min.js"></script>
<script src="${cpath}/assets/js/loader.js"></script>
<!-- page specific plugin scripts -->
<script src="${cpath}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${cpath}/assets/js/jquery.jqGrid.min.js"></script>
<#if user_language?exists&&user_language=='zh'>
    <script src="${cpath}/assets/js/grid.locale-cn.js?dt=${.now?datetime}"></script>
<#else>
    <script src="${cpath}/assets/js/grid.locale-en.js?dt=${.now?datetime}"></script>
</#if>
<script src="${cpath}/assets/js/jquery-ui.custom.min.js"></script>
<script src="${cpath}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${cpath}/assets/js/chosen.jquery.min.js"></script>
<script src="${cpath}/assets/js/spinbox.min.js"></script>
<script src="${cpath}/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${cpath}/assets/js/moment.min.js"></script>
<script src="${cpath}/assets/js/daterangepicker.min.js"></script>
<script src="${cpath}/assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="${cpath}/assets/js/bootstrap-colorpicker.min.js"></script>
<script src="${cpath}/assets/js/jquery.knob.min.js"></script>
<script src="${cpath}/assets/js/autosize.min.js"></script>
<script src="${cpath}/assets/js/jquery.inputlimiter.min.js"></script>
<script src="${cpath}/assets/js/jquery.maskedinput.min.js"></script>
<script src="${cpath}/assets/js/bootstrap-tag.min.js"></script>
<script src="${cpath}/assets/js/bootbox.js"></script>
<script src="${cpath}/assets/artDialog/artDialog.js"></script>
<script src="${cpath}/assets/artDialog/plugins/iframeTools.js"></script>
<script src="${cpath}/assets/js/customized/common.js"></script>
<!-- ace scripts -->
<script src="${cpath}/assets/js/ace-elements.min.js"></script>
<script src="${cpath}/assets/js/ace.min.js"></script>