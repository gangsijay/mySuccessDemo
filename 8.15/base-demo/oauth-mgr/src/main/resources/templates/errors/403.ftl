<#include "../layout/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title><@spring.message "tr.title"/></title>
    <#include "../layout/head.ftl" />
</head>

<body class="no-skin">
    <div class="main-container ace-save-state" id="main-container">
        <div class="main-content">
            <div class="main-content-inner">
                <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <div class="error-container">
                            <div class="well">
                                <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-sitemap"></i>
												403
											</span>
                                    Forbidden
                                </h1>

                                <hr />
                                <h3 class="lighter smaller">${message?default("无权访问！")}</h3>

                                <div>
                                    <div class="space"></div>
                                    <h4 class="smaller">Try one of the following:</h4>

                                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Re-check the url for typos
                                        </li>

                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Read the faq
                                        </li>

                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Tell us about it
                                        </li>
                                    </ul>
                                </div>

                                <hr />
                                <div class="space"></div>

                                <div class="center">
                                    <a href="javascript:history.back()" class="btn btn-grey">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        Go Back
                                    </a>

                                    <a href="/" class="btn btn-primary">
                                        <i class="ace-icon fa fa-tachometer"></i>
                                        Dashboard
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<#include "../layout/js.ftl" />
</body>
</html>