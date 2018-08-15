<#include "../../../layout/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title><@spring.message "tr.title"/></title>
<#include "../../../layout/head.ftl" />
</head>
<body class="no-skin">
<#include "../../../layout/navbar.ftl" />
<div class="main-container ace-save-state" id="main-container">
<#include "../../../layout/appmenu.ftl" />
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="page-header row">
                    <div class=" col-xs-6">
                        <h1>${user.username}<@spring.message "tr.urlMgr" /></h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" name="userUrlsForm" id="userUrlsForm" action="/admin/user/url/urlUpdate" method="post">

                        <#list groups as item>
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="checkbox">
                                        <label>
                                            <input name="groupid" type="checkbox" class="ace input-lg control_perm" data="control_perm${item.id}" value="${item.id}">
                                            <span class="lbl bigger-120 blue"> ${item.name} </span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-xs-12">
                                    <#list item.urls as subitem>
                                        <div class="col-xs-3 no-padding">
                                            <div class="checkbox">
                                                <label>
                                                    <input name="urlid" type="checkbox" class="ace sub_perm control_perm${item.id}" value="${subitem.id}" <#if subitem.checked==1>checked</#if>>
                                                    <span class="lbl"> ${subitem.name} </span>
                                                </label>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                            <hr />
                        </#list>
                            <input type="hidden" name="userid" value="${user.userid}">
                            <input type="hidden" name="cid" value="${cid}">
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                    </div><!-- /.col -->
                </div><!-- /.row -->
                <div class="row">
                    <div class="col-xs-12 text-right" >
                        <button class="btn btn-danger btn-sm" onclick="submitPermission()"><@spring.message "tr.submit" /></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../../../layout/js.ftl" />
<!-- inline scripts related to this page -->
<style>
    #jqgh_grid-table_cb{text-align: center}
</style>
<script type="text/javascript">
    $(function(){
        $('.control_perm').change(function() {
            var c_obj = $(this).attr('data');
            if ($(this).prop("checked")) {
                $('.'+c_obj).prop("checked", true);
            } else {
                $('.'+c_obj).prop("checked", false);
            }
        });
    });

    function submitPermission(){
        var frm = $('#userUrlsForm');
        $.ajax({
            url: frm.attr('action'),
            type: frm.attr('method'),
            data: getFormJson(frm),
            dataType: 'json',
            success: function (data) {
                if (data.code == 10000) {
                    alert(data.message);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("Submit Failed! Please contact your IT administrator");
            }
        });
    }
</script>
</body>
</html>