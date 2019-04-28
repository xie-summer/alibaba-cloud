/*
 Navicat Premium Data Transfer

 Source Server         : MySQL@root
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : cloud

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 04/29/2019 00:47:06 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `clientdetails`
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_approvals`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `oauth_client_details`
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('5ad953bf-5da4-4008-b519-16de233d52c8', '*', '4ad25ec5-dfaa-4958-be1a-d89757c28f5a', 'read,write', 'authorization_code', 'https://www.baidu.com', 'ROLE_ADMIN', '360000', '36000', null, null), ('afea9402-e79b-4aca-83d3-96d3d789c8e3', '*', '4ad25ec5-dfaa-4958-be1a-d89757c28f5a', 'read,write', 'password', 'https://www.baidu.com', 'ROLE_ADMIN', '360000', '36000', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `oauth_client_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_code`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `route_config`
-- ----------------------------
DROP TABLE IF EXISTS `route_config`;
CREATE TABLE `route_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `route_id` varchar(50) NOT NULL,
  `service_id` varchar(50) NOT NULL,
  `service_name` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `predicates` json DEFAULT NULL,
  `filters` json DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `operator` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `created_by` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `route_config`
-- ----------------------------
BEGIN;
INSERT INTO `route_config` VALUES ('5', 'CLOUD-EUREKA', 'CLOUD-EUREKA', 'CLOUD-EUREKA', '1', 'lb://CLOUD-EUREKA', '0', '[{\"args\": {\"pattern\": \"/cloud-eureka/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"regexp\": \"/cloud-eureka/(?<remaining>.*)\", \"replacement\": \"/${remaining}\"}, \"name\": \"RewritePath\"}]', '2018-11-28 23:34:56', '2018-11-28 23:34:56', 'DiscoveryClientRouteDefinitionLocator', '0', 'auto', 'auto'), ('6', 'GATEWAY-SERVER', 'GATEWAY-SERVER', 'GATEWAY-SERVER', '1', 'lb://GATEWAY-SERVER', '0', '[{\"args\": {\"pattern\": \"/gateway-server/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"regexp\": \"/gateway-server/(?<remaining>.*)\", \"replacement\": \"/${remaining}\"}, \"name\": \"RewritePath\"}]', '2018-11-28 23:34:59', '2018-11-28 23:34:59', 'DiscoveryClientRouteDefinitionLocator', '0', 'auto', 'auto'), ('7', 'EUREKA-SWAGGER', 'EUREKA-SWAGGER', 'EUREKA-SWAGGER', '1', 'lb://EUREKA-SWAGGER', '0', '[{\"args\": {\"pattern\": \"/eureka-swagger/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"regexp\": \"/eureka-swagger/(?<remaining>.*)\", \"replacement\": \"/${remaining}\"}, \"name\": \"RewritePath\"}]', '2018-12-15 19:22:29', '2018-12-15 19:22:29', 'DiscoveryClientRouteDefinitionLocator', '0', 'auto', 'auto');
COMMIT;

-- ----------------------------
--  Table structure for `t_roles`
-- ----------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '0 未删除 1删除',
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `t_roles`
-- ----------------------------
BEGIN;
INSERT INTO `t_roles` VALUES ('0', 'admin', 'ROLE_ADMIN', null, null, null, null, '2019-04-27 19:59:28');
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) NOT NULL,
  `sex` tinyint(10) DEFAULT '0' COMMENT '性别',
  `phone_number` varchar(50) DEFAULT '' COMMENT '手机号',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `status` tinyint(10) NOT NULL DEFAULT '0' COMMENT '状态 0 正常  1 无效',
  `introduce` varchar(250) DEFAULT '' COMMENT '简介',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '0 未删除  1 删除标记',
  `created_by` varchar(50) DEFAULT '' COMMENT '创建人',
  `last_modified_by` varchar(50) DEFAULT '' COMMENT '更新人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `last_modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'admin', '$2a$10$5IZnIzcJ6XVfPJ9JpfeeAe0PsNjVZsIWjnOdodqZbuQieMAIzcvF.', '1', '', '', '0', '', '0', '', '', '2019-04-26 00:39:12', '2019-04-26 00:39:15');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
