
/*用户表*/
DROP TABLE IF EXISTS user_info;
create table user_info(
   id INT NOT NULL AUTO_INCREMENT ,
   userid VARCHAR(32) NOT NULL ,
   username VARCHAR(32) NOT NULL ,
   password VARCHAR(32) NOT NULL ,
   mobile VARCHAR(11) NOT NULL ,
   avatar VARCHAR(255) NOT NULL ,
   status TINYINT NOT NULL ,
   gender VARCHAR(2) NOT NULL DEFAULT '男' ,
   email VARCHAR(255) NOT NULL ,
   entry_date TIMESTAMP NOT NULL DEFAULT current_timestamp ,
   last_login TIMESTAMP NOT NULL DEFAULT current_timestamp ,
   PRIMARY KEY ( id ),
   UNIQUE KEY ( userid,mobile,email )
)engine=innodb default charset=utf8 auto_increment=1;

/*角色表*/
DROP TABLE IF EXISTS role;
create table role(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(10) NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP  NOT NULL DEFAULT current_timestamp(),
   PRIMARY KEY ( id ),
   UNIQUE KEY ( client_id,name )
)engine=innodb default charset=utf8 auto_increment=1;

/*权限组*/
DROP TABLE IF EXISTS urls_group;
create table urls_group(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL DEFAULT current_timestamp() ,
   PRIMARY KEY ( id ),
   UNIQUE KEY ( client_id,name )
)engine=innodb default charset=utf8 auto_increment=1;

/*权限*/
DROP TABLE IF EXISTS urls;
create table urls(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   link_url VARCHAR(255) NOT NULL,
   is_menu TINYINT NOT NULL ,
   need_permission VARCHAR(255) NOT NULL ,
   group_id INT NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL DEFAULT current_timestamp() ,
   PRIMARY KEY ( id ),
   UNIQUE KEY ( client_id,name )
)engine=innodb default charset=utf8 auto_increment=1;

/*用户权限表*/
DROP TABLE IF EXISTS user_urls;
create table user_urls(
   id INT NOT NULL AUTO_INCREMENT,
   userid VARCHAR(32) NOT NULL,
   urlsid int NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  DEFAULT current_timestamp(),
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8 auto_increment=1;

/*角色权限表*/
DROP TABLE IF EXISTS role_urls;
create table role_urls(
   id INT NOT NULL AUTO_INCREMENT,
   roleid int NOT NULL,
   urlsid int NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  DEFAULT current_timestamp(),
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8 auto_increment=1;

/*用户角色表*/
DROP TABLE IF EXISTS user_role;
create table user_role(
   id INT NOT NULL AUTO_INCREMENT,
   roleid int NOT NULL,
   userid VARCHAR(32) NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  DEFAULT current_timestamp(),
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8 auto_increment=1;

/*app应用用户表*/
DROP TABLE IF EXISTS app_user;
create table app_user(
  id INT NOT NULL AUTO_INCREMENT,
  client_id VARCHAR(100) NOT NULL,
  userid VARCHAR(32) NOT NULL,
  isadmin INT(2) NOT NULL DEFAULT 0,
  PRIMARY KEY ( id ),
  UNIQUE KEY ( client_id,userid )
)engine=innodb default charset=utf8 auto_increment=1;

/*OAuth 应用表*/
DROP TABLE IF EXISTS oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(100) PRIMARY KEY,
  client_name VARCHAR(100),
  resource_ids VARCHAR(100),
  client_secret VARCHAR(100),
  scope VARCHAR(100),
  authorized_grant_types VARCHAR(100),
  web_server_redirect_uri VARCHAR(100),
  authorities VARCHAR(100),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  status TINYINT NOT NULL DEFAULT 0,
  userid VARCHAR(32) NOT NULL ,
  entry_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  autoapprove VARCHAR(100)
);

DROP TABLE IF EXISTS oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(100),
  token blob,
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(100),
  client_id VARCHAR(100)
);

DROP TABLE IF EXISTS oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(100),
  token blob,
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(100),
  client_id VARCHAR(100),
  authentication blob,
  refresh_token VARCHAR(100)
);

DROP TABLE IF EXISTS oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(100),
  token blob,
  authentication blob
);

DROP TABLE IF EXISTS oauth_code;
create table oauth_code (
  code VARCHAR(100), authentication blob
);

DROP TABLE IF EXISTS oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(100),
	clientId VARCHAR(100),
	scope VARCHAR(100),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);

DROP TABLE IF EXISTS ClientDetails;
-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(100) PRIMARY KEY,
  resourceIds VARCHAR(100),
  appSecret VARCHAR(100),
  scope VARCHAR(100),
  grantTypes VARCHAR(100),
  redirectUrl VARCHAR(100),
  authorities VARCHAR(100),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(100)
);