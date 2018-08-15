<#include "layout/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "layout/head.ftl" />
</head>
<body class="no-skin">
    <#include "layout/navbar.ftl" />
    <div class="main-container ace-save-state" id="main-container">
        <#include "layout/menu.ftl" />
        <div class="main-content">
            <div class="main-content-inner">
                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            后台管理
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="alert alert-info">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                <strong>欢迎!</strong>
                                <br>
                            </div>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div>
            </div>
        </div>
    </div>
    <#include "layout/js.ftl" />
    <!-- inline scripts related to this page -->
</body>
</html>