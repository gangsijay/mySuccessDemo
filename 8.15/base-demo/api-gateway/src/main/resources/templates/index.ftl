<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>接口说明</title>
    <link href="/bootstrap.min.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="container">

    <h1>接口说明</h1>

    <pre><code>获取access_token  http://url/user/login  POST提交username与password</code></pre>

    <p>返回如下格式：</p>

<pre><code>{
  &quot;code&quot;: &quot;SUCCESS&quot;,
  &quot;message&quot;: {
    &quot;access_token&quot;: &quot;302414be-10d2-48c3-b1f2-218b5bd2a30e&quot;,
    &quot;token_type&quot;: &quot;bearer&quot;,
    &quot;refresh_token&quot;: &quot;0f89109c-167a-45c5-9e87-0aa509c369c1&quot;,
    &quot;expires_in&quot;: 599485,
    &quot;scope&quot;: &quot;read write trust&quot;
  },
  &quot;requestUri&quot;: &quot;/login&quot;,
  &quot;status&quot;: 10000
}</code></pre>

    <p>其中安卓客户端需要解析保存access_token与refresh_token,之后在登录状态下的每次请求都要在Request Header里面带上access_token。格式为:</p>

    <pre><code>Authorization:Bearer 302414be-10d2-48c3-b1f2-218b5bd2a30e</code></pre>

    <p>当请求一个接口返回如下格式时，说明access_token已经失效。</p>

<pre><code>{
  &quot;error&quot;: &quot;invalid_token&quot;,
  &quot;error_description&quot;: &quot;302414be-10d2-48c3-b1f2-218b5bd2a30e&quot;
}</code></pre>

    <p>这时，需要拿登录时候保存的refresh_token去重新获取access_token,获取接口如下：</p>

    <pre><code>http://url/user/refresh_to_token    带上refresh_token参数</code></pre>

    <h1>接口文档地址</h1>

    <h2>用户接口文档</h2>

    <pre><a href="/user/swagger-ui.html"><code>点击前往</code></a></pre>

    <h2>公共服务接口文档</h2>

<pre><a href="/common/swagger-ui.html"><code>点击前往
</code></pre></a>



</div>
</body>
</html>