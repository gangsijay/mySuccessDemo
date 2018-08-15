<#include "../../layout/common.ftl" />
<#include "../../layout/head.ftl" />
<body class="no-skin">
<div class="main-container ace-save-state" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="urls_name"> <@spring.message "tr.urls.name" /> </label>

                                <div class="col-sm-9">
                                    <input type="text" id="urls_name" placeholder="<@spring.message "tr.urls.name" />" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="urls_link_url"> <@spring.message "tr.urls.link_url" /> </label>

                                <div class="col-sm-9">
                                    <input type="text" id="urls_link_url" placeholder="<@spring.message "tr.urls.link_url" />" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="urls_group"> <@spring.message "tr.urls.group" /> </label>

                                <div class="col-sm-9">
                                    <select class="form-control" id="urls_group">
                                        <#list groups as item>
                                            <option value="${item.id}">${item.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <a href="#" onclick="groupMgr()"><@spring.message "tr.urlsGroupMgr" /></a>
                            <div class="space-4"></div>
                        </form>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>
        </div>
    </div>
</div>
<#include "../../layout/js.ftl" />
<script type="text/javascript">
    function groupMgr(){
        art.dialog.open("/admin/urlsGroup/list",{
            title:'<@spring.message "tr.urlsGroupMgr" />',
            width:800,
            height:450,
            lock:true
        });
    }
</script>

</body>
