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
                                <label class="col-sm-3 control-label no-padding-right" for="client_id"> <@spring.message "tr.oauthClientId" /> </label>

                                <div class="col-sm-9">
                                    <input type="text" id="client_id" placeholder="<@spring.message "tr.oauthClientId" />" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="client_name"> <@spring.message "tr.oauthClientName" /> </label>

                                <div class="col-sm-9">
                                    <input type="text" id="client_name" placeholder="<@spring.message "tr.oauthClientName" />" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="web_server_redirect_uri"> <@spring.message "tr.callbackUrl" /> </label>

                                <div class="col-sm-9">
                                    <input type="text" id="web_server_redirect_uri" placeholder="<@spring.message "tr.callbackUrl" />" class="form-control">
                                </div>
                            </div>


                        </form>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>
        </div>
    </div>
</div>
<#include "../../layout/js.ftl" />
</body>
