/*
Navicat PGSQL Data Transfer

Source Server         : localhost
Source Server Version : 90304
Source Host           : localhost:5432
Source Database       : lgtest
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90304
File Encoding         : 65001

Date: 2018-08-13 17:41:28
*/


-- ----------------------------
-- Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE "public"."hibernate_sequence";
CREATE SEQUENCE "public"."hibernate_sequence"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 9
 CACHE 1;
SELECT setval('"public"."hibernate_sequence"', 9, true);

-- ----------------------------
-- Table structure for lg_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."lg_permission";
CREATE TABLE "public"."lg_permission" (
"id" int8 NOT NULL,
"descritpion" varchar(130) COLLATE "default",
"name" varchar(30) COLLATE "default",
"pid" int8,
"url" varchar(130) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lg_permission
-- ----------------------------
INSERT INTO "public"."lg_permission" VALUES ('1', '管理员页面', 'admin', null, '/admin');
INSERT INTO "public"."lg_permission" VALUES ('2', '首页', 'home', null, '/home');
INSERT INTO "public"."lg_permission" VALUES ('3', '普通用户', 'user', null, '/userhome');

-- ----------------------------
-- Table structure for lg_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."lg_role";
CREATE TABLE "public"."lg_role" (
"id" int8 NOT NULL,
"name" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lg_role
-- ----------------------------
INSERT INTO "public"."lg_role" VALUES ('1', 'ROLE_USER');
INSERT INTO "public"."lg_role" VALUES ('2', 'ROLE_ADMIN');
INSERT INTO "public"."lg_role" VALUES ('3', 'ROLE_GUEST');

-- ----------------------------
-- Table structure for lg_role_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."lg_role_permission";
CREATE TABLE "public"."lg_role_permission" (
"id" int8 NOT NULL,
"sys_permission_id" int8,
"sys_role_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lg_role_permission
-- ----------------------------
INSERT INTO "public"."lg_role_permission" VALUES ('1', '1', '2');
INSERT INTO "public"."lg_role_permission" VALUES ('2', '2', '2');
INSERT INTO "public"."lg_role_permission" VALUES ('3', '3', '2');

-- ----------------------------
-- Table structure for lg_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."lg_user";
CREATE TABLE "public"."lg_user" (
"id" int8 NOT NULL,
"password" varchar(30) COLLATE "default",
"name" varchar(30) COLLATE "default",
"username" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lg_user
-- ----------------------------
INSERT INTO "public"."lg_user" VALUES ('1', '123456', 'admin', 'admin');

-- ----------------------------
-- Table structure for lg_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."lg_user_role";
CREATE TABLE "public"."lg_user_role" (
"id" int8 NOT NULL,
"sys_role_id" int8,
"sys_user_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lg_user_role
-- ----------------------------
INSERT INTO "public"."lg_user_role" VALUES ('1', '2', '1');

-- ----------------------------
-- Table structure for lgg_authority
-- ----------------------------
DROP TABLE IF EXISTS "public"."lgg_authority";
CREATE TABLE "public"."lgg_authority" (
"id" int8 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"value" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lgg_authority
-- ----------------------------
INSERT INTO "public"."lgg_authority" VALUES ('1', '2018-08-09 18:02:11.407', '2018-08-09 18:02:11.407', '查询', '/home');
INSERT INTO "public"."lgg_authority" VALUES ('4', '2018-08-10 10:03:06.355', '2018-08-10 10:03:06.355', '查询', '/home');
INSERT INTO "public"."lgg_authority" VALUES ('7', '2018-08-10 10:09:10.371', '2018-08-10 10:09:10.371', '查询', '/home');

-- ----------------------------
-- Table structure for lgg_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."lgg_role";
CREATE TABLE "public"."lgg_role" (
"id" int8 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"value" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lgg_role
-- ----------------------------
INSERT INTO "public"."lgg_role" VALUES ('2', '2018-08-09 18:02:11.506', '2018-08-09 18:02:11.506', '管理员', 'ROLE_ADMIN');
INSERT INTO "public"."lgg_role" VALUES ('3', '2018-08-09 18:02:11.548', '2018-08-09 18:02:11.548', '普通用户', 'ROLE_USER');
INSERT INTO "public"."lgg_role" VALUES ('5', '2018-08-10 10:03:06.688', '2018-08-10 10:03:06.688', '管理员', 'ROLE_ADMIN');
INSERT INTO "public"."lgg_role" VALUES ('6', '2018-08-10 10:03:06.755', '2018-08-10 10:03:06.755', '普通用户', 'ROLE_USER');
INSERT INTO "public"."lgg_role" VALUES ('8', '2018-08-10 10:09:10.715', '2018-08-10 10:09:10.715', '管理员', 'ROLE_ADMIN');
INSERT INTO "public"."lgg_role" VALUES ('9', '2018-08-10 10:09:10.807', '2018-08-10 10:09:10.807', '普通用户', 'ROLE_USER');

-- ----------------------------
-- Table structure for lgg_role_authorities
-- ----------------------------
DROP TABLE IF EXISTS "public"."lgg_role_authorities";
CREATE TABLE "public"."lgg_role_authorities" (
"role_id" int8 NOT NULL,
"authorities_id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lgg_role_authorities
-- ----------------------------
INSERT INTO "public"."lgg_role_authorities" VALUES ('2', '1');
INSERT INTO "public"."lgg_role_authorities" VALUES ('3', '1');
INSERT INTO "public"."lgg_role_authorities" VALUES ('5', '1');
INSERT INTO "public"."lgg_role_authorities" VALUES ('8', '1');

-- ----------------------------
-- Table structure for lgg_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."lgg_user";
CREATE TABLE "public"."lgg_user" (
"id" int8 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"update_time" timestamp(6) NOT NULL,
"email" varchar(255) COLLATE "default",
"first_name" varchar(255) COLLATE "default",
"image_url" varchar(255) COLLATE "default",
"last_name" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
"pass" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lgg_user
-- ----------------------------
INSERT INTO "public"."lgg_user" VALUES ('1', '2018-08-09 18:02:11.506', '2018-08-09 18:02:11.506', null, null, null, null, 'fwf', 'fwf');
INSERT INTO "public"."lgg_user" VALUES ('2', '2018-08-09 18:02:11.506', '2018-08-09 18:02:11.506', null, null, null, null, 'wl', 'wl');

-- ----------------------------
-- Table structure for lgg_user_roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."lgg_user_roles";
CREATE TABLE "public"."lgg_user_roles" (
"user_id" int8 NOT NULL,
"roles_id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lgg_user_roles
-- ----------------------------
INSERT INTO "public"."lgg_user_roles" VALUES ('1', '2');
INSERT INTO "public"."lgg_user_roles" VALUES ('2', '3');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table lg_permission
-- ----------------------------
ALTER TABLE "public"."lg_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lg_role
-- ----------------------------
ALTER TABLE "public"."lg_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lg_role_permission
-- ----------------------------
ALTER TABLE "public"."lg_role_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lg_user
-- ----------------------------
ALTER TABLE "public"."lg_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lg_user_role
-- ----------------------------
ALTER TABLE "public"."lg_user_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lgg_authority
-- ----------------------------
ALTER TABLE "public"."lgg_authority" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lgg_role
-- ----------------------------
ALTER TABLE "public"."lgg_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lgg_role_authorities
-- ----------------------------
ALTER TABLE "public"."lgg_role_authorities" ADD PRIMARY KEY ("role_id", "authorities_id");

-- ----------------------------
-- Primary Key structure for table lgg_user
-- ----------------------------
ALTER TABLE "public"."lgg_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table lgg_user_roles
-- ----------------------------
ALTER TABLE "public"."lgg_user_roles" ADD PRIMARY KEY ("user_id", "roles_id");

-- ----------------------------
-- Foreign Key structure for table "public"."lgg_role_authorities"
-- ----------------------------
ALTER TABLE "public"."lgg_role_authorities" ADD FOREIGN KEY ("authorities_id") REFERENCES "public"."lgg_authority" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."lgg_role_authorities" ADD FOREIGN KEY ("role_id") REFERENCES "public"."lgg_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."lgg_user_roles"
-- ----------------------------
ALTER TABLE "public"."lgg_user_roles" ADD FOREIGN KEY ("roles_id") REFERENCES "public"."lgg_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."lgg_user_roles" ADD FOREIGN KEY ("user_id") REFERENCES "public"."lgg_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
