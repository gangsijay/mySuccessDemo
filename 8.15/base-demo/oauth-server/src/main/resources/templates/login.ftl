<#include "layout/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><@spring.message "tr.loginTitle"/></title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${cpath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${cpath}/login/css/theme.css">
    <link rel="stylesheet" href="${cpath}/assets/font-awesome/4.5.0/css/font-awesome.min.css">
    <script src="${cpath}/assets/js/jquery-2.1.4.min.js"></script>
    <script src="${cpath}/login/js/login.js" type="text/javascript"></script>
    <script>
        $(function(){
            $('#btn-login').click(function(){
                LOGIN.dologin();
            });
        });
    </script>

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${cpath}/login/js/html5shiv.min.js"></script>
    <script src="${cpath}/login/js/respond.min.js"></script>
    <![endif]-->
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body>
<!--<![endif]-->

<div class="container">
    <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 loginbox">
        <div class="panel panel-info" >
            <div class="panel-heading">
                <div class="panel-title"><@spring.message "tr.loginTitle"/></div>
            </div>
            <div class="panel-body panel-pad">
                <#if error?exists >
                    <div id="login-alert" class="alert alert-danger col-sm-12"><@spring.message "tr.loginError"/></div>
                </#if>

                <form id="login_form" action="${cpath}/login" method="post" class="form-horizontal" role="form">
                    <div class="input-group margT25">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
                        <input id="username" type="text" class="form-control" name="username" value="" placeholder="<@spring.message "tr.loginUsername"/>">
                    </div>
                    <div class="input-group margT25">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" value="" placeholder="<@spring.message "tr.loginPassword"/>" onkeypress="if(event.keyCode==13){LOGIN.dologin();}">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                    </div>
                    <div class="form-group margT10">
                        <!-- Button -->
                        <div class="col-sm-12 controls">
                            <a id="btn-login" href="#" class="btn btn-block btn-success"><@spring.message "tr.loginBtn"/></a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${cpath}/assets/js/bootstrap.min.js"></script>
<script>
    var LOGIN = {
        dologin:function() {
            var username = $('#username').val();
            var password = $('#password').val();
            if ($.trim(username) == '') {
                alert("<@spring.message "tr.loginUsernameBlank"/>");
                return false;
            }
            if ($.trim(password) == '') {
                alert("<@spring.message "tr.loginPasswordBlank"/>");
                return false;
            }
            $('#login_form').submit();
        }
    };
</script>
</body>
</html>


