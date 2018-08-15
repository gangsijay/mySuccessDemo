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
                        <h1>${userInfo.username}<@spring.message "tr.roleMgr" /></h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" name="roleUrlsForm" id="roleUrlsForm" action="/admin/user/role/roleUpdate" method="post">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="checkbox">
                                    <label>
                                        <input name="selall" type="checkbox" class="ace control_role input-lg" data="control_all_role" value="1">
                                        <span class="lbl bigger-120 blue"> 全选 </span>
                                    </label>
                                </div>
                            </div>
                            <#list roles as item>
                                    <div class="col-xs-3">
                                        <div class="checkbox">
                                            <label>
                                                <input name="roleids" type="checkbox" class="ace control_all_role"  value="${item.id}" <#if item.checked==1>checked</#if>>
                                                <span class="lbl"> ${item.name} </span>
                                            </label>
                                        </div>
                                    </div>
                            </#list>
                        </div>
                            <hr />
                            <input type="hidden" name="userid" value="${userInfo.userid}">
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
        $('.control_role').change(function() {
            var c_obj = $(this).attr('data');
            if ($(this).prop("checked")) {
                $('.'+c_obj).prop("checked", true);
            } else {
                $('.'+c_obj).prop("checked", false);
            }
        });
    })
    function submitPermission(){
        $('#roleUrlsForm').submit();
    }
</script>
</body>
</html>