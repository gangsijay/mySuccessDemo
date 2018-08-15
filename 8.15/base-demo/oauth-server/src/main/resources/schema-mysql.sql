
/*用户表*/
create table user_info(
   id INT NOT NULL ,
   userid VARCHAR(32) NOT NULL ,
   username VARCHAR(32) NOT NULL ,
   password VARCHAR(32) NOT NULL ,
   mobile VARCHAR(11) NOT NULL ,
   avatar VARCHAR(255) NOT NULL ,
   status int NOT NULL ,
   gender VARCHAR(2) NOT NULL DEFAULT '男' ,
   email VARCHAR(255) NOT NULL ,
   entry_date TIMESTAMP NOT NULL DEFAULT current_timestamp ,
   last_login TIMESTAMP NOT NULL DEFAULT current_timestamp ,
   area VARCHAR(255),
   age INT ,
   driver_type VARCHAR(255),
   ID_Num VARCHAR(255),
   has_certificate INT,
   PRIMARY KEY ( id )
);

/*角色表*/

create table role (
   id INT NOT NULL ,
   name VARCHAR(10) NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP  NOT NULL ,
   PRIMARY KEY ( id )
);

/*权限组*/

create table urls_group(
   id INT NOT NULL ,
   name VARCHAR(100) NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL ,
   PRIMARY KEY ( id )
);

/*权限*/

create table urls(
   id INT NOT NULL ,
   name VARCHAR(100) NOT NULL,
   link_url VARCHAR(255) NOT NULL,
   is_menu INT NOT NULL ,
   need_permission VARCHAR(255) NOT NULL ,
   group_id INT NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  ,
   PRIMARY KEY ( id )
);

/*用户权限表*/

create table user_urls(
   id INT NOT NULL ,
   userid VARCHAR(32) NOT NULL,
   urlsid int NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  ,
   PRIMARY KEY ( id )
);

/*角色权限表*/

create table role_urls(
   id INT NOT NULL ,
   roleid int NOT NULL,
   urlsid int NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  ,
   PRIMARY KEY ( id )
);

/*用户角色表*/

create table user_role(
   id INT NOT NULL ,
   roleid int NOT NULL,
   userid VARCHAR(32) NOT NULL,
   client_id VARCHAR(100) NOT NULL,
   entry_date TIMESTAMP NOT NULL  ,
   PRIMARY KEY ( id )
);

/*app应用用户表*/

create table app_user(
  id INT NOT NULL ,
  client_id VARCHAR(100) NOT NULL,
  userid VARCHAR(32) NOT NULL,
  isadmin INT  NOT NULL ,
  PRIMARY KEY ( id )
);

/*OAuth 应用表*/

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
  status INT NOT NULL DEFAULT 0,
  userid VARCHAR(32) NOT NULL ,
  entry_date TIMESTAMP NOT NULL  ,
  autoapprove VARCHAR(100)
);


create table oauth_client_token (
  token_id VARCHAR(100),
  token VARCHAR(255),
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(100),
  client_id VARCHAR(100)
);


create table oauth_access_token (
  token_id VARCHAR(100),
  token VARCHAR(255),
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(100),
  client_id VARCHAR(100),
  authentication VARCHAR(255),
  refresh_token VARCHAR(100)
);


create table oauth_refresh_token (
  token_id VARCHAR(100),
  token VARCHAR(255),
  authentication VARCHAR(255)
);


create table oauth_code (
  code VARCHAR(100), authentication VARCHAR(255)
);


create table oauth_approvals (
	userId VARCHAR(100),
	clientId VARCHAR(100),
	scope VARCHAR(100),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP 
);


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