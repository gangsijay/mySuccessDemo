# mySuccessDemo
参考了https://blog.csdn.net/fanbojiayou/article/details/79323315里子，修改BCryptPasswordEncoder、RedisTokenStore对应自己的实现
MyBCryptPasswordEncoder、MyRedisTokenStore

INSERT INTO "public"."lgg_authority" ("id", "create_time", "update_time", "name", "value") VALUES ('1', '2018-08-09 18:02:11.407', '2018-08-09 18:02:11.407', '查询', 'query');
INSERT INTO "public"."lgg_role" ("id", "create_time", "update_time", "name", "value") VALUES ('2', '2018-08-09 18:02:11.506', '2018-08-09 18:02:11.506', '管理员', 'ROLE_ADMIN');
INSERT INTO "public"."lgg_role" ("id", "create_time", "update_time", "name", "value") VALUES ('3', '2018-08-09 18:02:11.548', '2018-08-09 18:02:11.548', '普通用户', 'ROLE_USER');
INSERT INTO "public"."lgg_role_authorities" ("role_id", "authorities_id") VALUES ('2', '1');
INSERT INTO "public"."lgg_role_authorities" ("role_id", "authorities_id") VALUES ('3', '1');
INSERT INTO "public"."lgg_user" ("id", "create_time", "update_time", "email", "first_name", "image_url", "last_name", "name", "pass") VALUES ('1', '2018-08-09 18:02:11.506', '2018-08-09 18:02:11.506', NULL, NULL, NULL, NULL, 'fwf', 'fwf');
INSERT INTO "public"."lgg_user" ("id", "create_time", "update_time", "email", "first_name", "image_url", "last_name", "name", "pass") VALUES ('2', '2018-08-09 18:02:11.506', '2018-08-09 18:02:11.506', NULL, NULL, NULL, NULL, 'wl', 'wl');
INSERT INTO "public"."lgg_user_roles" ("user_id", "roles_id") VALUES ('1', '2');
INSERT INTO "public"."lgg_user_roles" ("user_id", "roles_id") VALUES ('2', '3');

测试步骤：

spring Cloud 基于网关的统一授权认证
本项目基于汪云飞记录本Github地址由于不好部署需要导入数据库等原因本人稍微做了一些改进,但总体上还是相似的,只是更容易跑起来,省去了导入数据库等麻烦的操作。 如果对Spring Boot开发感兴趣可以看看JavaEE开发的颠覆者: Spring Boot实战作者也是汪云飞.

使用OAuth2实现多个微服务的统一认证授权,通过向OAUTH服务发送某个类型的grant type进行集中认证和授权获得access token,这个access token是受其他微服务信任的。后续访问中可以通过这个access token来进行。

account: 用户微服务
xfauth: OAUTH2认证授权中心
gateway: 边界网关
eureka: 服务注册和发现
基础环境
开启MySql 修改xfauth配置文件bootstrap.yml中的datasource配置mysql用户名、密码、数据库名。
开启Redis 修改xfauth配置文件bootstrap.yml中的redis如果默认端口号是6379 host为 localhost 则不用修改。
项目中使用了lombok如果你的IDE是Eclipse需要安装相应的插件,如果是IDEA2017版本的不用安装插件已经支持。
运行
运行eureka 端口号8888
运行gateway 端口号8088
运行xfauth(因为使用了JPA会自动创建数据表不用导入数据库,只需要开启mysql) 端口号5000

账户1: username:fpf password:fpf
账户2: username:wl password:wl
相关的设置可以在xfauth项目中的Init类中看到
运行account 端口号8083
测试
通关zuul网关访问认证服务获取 access token 8088是网关端口 

