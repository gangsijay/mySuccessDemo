#App项目脚手架
##架构图
###App接口
![App](https://raw.githubusercontent.com/zhmlvft/base-demo/master/screenshots/App.png  "App")
###统一认证
####OAuth交互流程
![OAuth](https://raw.githubusercontent.com/zhmlvft/base-demo/master/screenshots/OAuth.png  "OAuth")
####OAuth授权模式
#####密码授权，App认证使用这种。
![OAuthPasswd](https://raw.githubusercontent.com/zhmlvft/base-demo/master/screenshots/OAuthPasswd.png  "OAuthPasswd")
#####授权码授权，后台认证使用这种。
![OAuthCode](https://raw.githubusercontent.com/zhmlvft/base-demo/master/screenshots/OAuthCode.png  "OAuthCode")
##使用的技术
主要使用spring boot的技术栈。有web、security、jdbc、mybatis、freemarker、OAuth、Zuul等。
##快速启动
###环境
JDK1.8、Redis、MySql。
修改各个工程的application.properties中的数据库连接信息。
###启动步骤
    1.启动oauth-server提供认证服务。
    2.启动oauth-mgr提供OAuth用户以及权限管理后台。
    3.启动api-gateway提供gateway的转发功能。
    4.启动api-user提供用户服务接口功能。
    5.启动api-common提供公共服务功能。
    6.启动mgr-server应用提供后台管理功能。（可选）

##测试
###帐号密码
初始帐号为：13811111111
初始密码为：123456
###Api测试
打开浏览器访问http://localhost:9090/ ,即可进入api网关。里面有访问Api的注意事项。
###OAuth管理后台
访问http://localhost:8089/ 进入，在后台可以设置某个应用允许哪些人访问，以及这些人在该应用中的权限是什么。
###后台管理系统
http://localhost:9094