<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>授权登录</title>
    <link rel="stylesheet" href="${cpath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${cpath}/login/css/theme.css">
    <link rel="stylesheet" href="${cpath}/assets/font-awesome/4.5.0/css/font-awesome.min.css">
    <script src="${cpath}/assets/js/jquery-2.1.4.min.js"></script>
    <script src="${cpath}/assets/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <h1>授权页面</h1>

    <#if SPRING_SECURITY_LAST_EXCEPTION?exists>
        <div class="error">
            <h2>Woops!</h2>
            <p>
                Access could not be granted. (<p th:text="${SPRING_SECURITY_LAST_EXCEPTION.getMessage()}"></p>)
            </p>
        </div>
    </#if>

    <h2>Please Confirm</h2>

    <p>
        You hereby authorize ${client.clientId} to access your protected resources.
    </p>

    <form id="confirmationForm" name="confirmationForm"
          action="${cpath}/oauth/authorize" method="post">
        <input name="user_oauth_approval" value="true" type="hidden" />
        <ul class="list-unstyled">
        <#if scopes?exists>
            <#list scopes?keys as key>
                <div class="form-group">
                    ${key}</span>: <input type="radio" name="${key}"
                                                value="true" checked />允许
                    <input type="radio"
                           name="${key}" value="false" />拒绝
                </div>
            </#list>
        </#if>
        </ul>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Submit</button>
    </form>


</div>

</body>
</html>
