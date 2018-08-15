insert into user_info (id,userid,username,password,mobile,email,entry_date,avatar,status,last_login) values(1,'5788dbd970f00b24c2812c54','庄海明','e10adc3949ba59abbe56e057f20f883e','13811111111','286600136@qq.com',CURRENT_TIMESTAMP(),'',1,CURRENT_TIMESTAMP());
insert into oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,client_name,userid,status) values('demo_app','demo_user','5788dc4f70f00b2586a19c95','read,write,trust','password,authorization_code,refresh_token','http://localhost:8089/login','',null,null,'{}','true','Demo App','5788dbd970f00b24c2812c54',1);

insert into oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,client_name,userid,status) values('demo_admin','demo_user','5788dc4670f00b25521360d1','read,write,trust','password,authorization_code,refresh_token','http://localhost:9094/login','',null,null,'{}','true','Demo Mgr','5788dbd970f00b24c2812c54',1);

insert into oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,client_name,userid,status) values('demo_oauth_admin','demo_user','5788dc4f70f00b2586a19c95','read,write,trust','password,authorization_code,refresh_token','http://localhost:8089/login','',null,null,'{}','true','Demo OAuth Mgr','5788dbd970f00b24c2812c54',1);


insert into app_user (id,client_id,userid,isadmin) values(1,'demo_app','5788dbd970f00b24c2812c54',1);
insert into app_user (id,client_id,userid,isadmin) values(2,'demo_admin','5788dbd970f00b24c2812c54',1);
insert into app_user (id,client_id,userid,isadmin) values(3,'demo_oauth_admin','5788dbd970f00b24c2812c54',1);

insert into role(id,name,client_id) values(1,'角色1','demo_admin');
insert into role(id,name,client_id) values(2,'角色2','demo_admin');

insert into urls_group(id,name,client_id) values(1,'用户管理','demo_admin');
insert into user_role(id,userid,roleid,client_id) values(1,'5788dbd970f00b24c2812c54',1,'xc_admin');

insert into role_urls(id,roleid,urlsid,client_id) values(1,1,1,'demo_admin');
insert into role_urls(id,roleid,urlsid,client_id) values(2,1,2,'demo_admin');


insert into urls(id,name,link_url,group_id,is_menu,need_permission,client_id) values(1,'查询用户','/admin/user/list',1,1,'/admin/user/list','demo_admin');
insert into urls(id,name,link_url,group_id,is_menu,need_permission,client_id) values(2,'新增用户','/admin/user/editData',1,0,'/admin/user/add','demo_admin');
insert into urls(id,name,link_url,group_id,is_menu,need_permission,client_id) values(3,'删除用户','/admin/user/editData',1,0,'/admin/user/del','demo_admin');
insert into urls(id,name,link_url,group_id,is_menu,need_permission,client_id) values(4,'修改用户','/admin/user/editData',1,0,'/admin/user/edit','demo_admin');