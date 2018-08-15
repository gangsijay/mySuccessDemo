<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/loader.js"></script>
<!-- page specific plugin scripts -->
<script src="/assets/js/bootstrap-datepicker.min.js"></script>
<script src="/assets/js/jquery.jqGrid.min.js"></script>
<#if user_language?exists&&user_language=='zh'>
    <script src="/assets/js/grid.locale-cn.js?dt=${.now?datetime}"></script>
<#else>
    <script src="/assets/js/grid.locale-en.js?dt=${.now?datetime}"></script>
</#if>
<!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
     This must be loaded before fileinput.min.js -->
<script src="/assets/fileinput/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
     This must be loaded before fileinput.min.js -->
<script src="/assets/fileinput/js/plugins/sortable.min.js" type="text/javascript"></script>
<!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
     This must be loaded before fileinput.min.js -->
<script src="/assets/fileinput/js/plugins/purify.min.js" type="text/javascript"></script>
<!-- the main fileinput plugin file -->
<script src="/assets/fileinput/js/fileinput.min.js"></script>
<script src="/assets/js/jquery-ui.custom.min.js"></script>
<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="/assets/js/chosen.jquery.min.js"></script>
<script src="/assets/js/spinbox.min.js"></script>
<script src="/assets/js/bootstrap-timepicker.min.js"></script>
<script src="/assets/js/moment.min.js"></script>
<script src="/assets/js/daterangepicker.min.js"></script>
<script src="/assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="/assets/js/bootstrap-colorpicker.min.js"></script>
<script src="/assets/js/jquery.knob.min.js"></script>
<script src="/assets/js/autosize.min.js"></script>
<script src="/assets/js/jquery.inputlimiter.min.js"></script>
<script src="/assets/js/jquery.maskedinput.min.js"></script>
<script src="/assets/js/bootstrap-tag.min.js"></script>
<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/my.js"></script>
<script src="/assets/artDialog/artDialog.js"></script>
<script src="/assets/artDialog/plugins/iframeTools.js"></script>
<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>
<!-- optionally if you need a theme like font awesome theme you can include 
    it as mentioned below -->
<script src="/assets/fileinput/themes/fa/theme.js"></script>
<!-- optionally if you need translation for your language then include 
    locale file as mentioned below -->
<#if user_language?exists&&user_language=='zh'>
<script src="/assets/fileinput/js/locales/zh.js"></script>
<#else>
<script src="/assets/fileinput/js/locales/LANG.js"></script>
</#if>