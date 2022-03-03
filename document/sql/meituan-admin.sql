/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.35 : Database - meituan-admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meituan-admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `meituan-admin`;

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';

/*Data for the table `schedule_job` */

insert  into `schedule_job`(`job_id`,`bean_name`,`params`,`cron_expression`,`status`,`remark`,`create_time`) values (1,'testTask','renren','0 0/30 * * * ?',0,'参数测试','2021-11-19 03:37:01');

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务日志';

/*Data for the table `schedule_job_log` */

insert  into `schedule_job_log`(`log_id`,`job_id`,`bean_name`,`params`,`status`,`error`,`times`,`create_time`) values (1,1,'testTask','renren',0,NULL,0,'2021-11-25 11:00:01'),(2,1,'testTask','renren',0,NULL,0,'2021-11-25 11:30:00'),(3,1,'testTask','renren',0,NULL,0,'2021-11-25 12:00:00'),(4,1,'testTask','renren',0,NULL,0,'2021-11-25 13:00:00'),(5,1,'testTask','renren',0,NULL,0,'2021-11-25 13:30:00');

/*Table structure for table `sys_captcha` */

DROP TABLE IF EXISTS `sys_captcha`;

CREATE TABLE `sys_captcha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=582 DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';

/*Data for the table `sys_captcha` */

insert  into `sys_captcha`(`id`,`uuid`,`code`,`expire_time`) values (532,'00787141-5df3-4d3c-8a92-5b017e2d39b0','ma8yd','2021-12-20 20:20:50'),(534,'c88a25e4-e31f-41cb-8edc-4c5663a8d8b6','3ex8x','2021-12-20 20:21:36'),(535,'13cc15ad-5cc1-492e-84bb-4f48b3b459ff','fenfc','2021-12-23 18:59:27'),(536,'35bebee1-9a59-4cfb-82ab-f4d11358bed0','4645x','2021-12-23 19:01:04'),(537,'6f861a5c-dd98-48bb-8096-f056e78990d1','gn6na','2021-12-23 19:01:20'),(538,'94b4bff9-acf6-419e-8d25-e64bae4c5a47','m2dy4','2021-12-23 19:02:02'),(540,'deb15cfe-7b24-4cf6-8a27-a4ff1cd8e52a','4355e','2021-12-23 19:02:24'),(541,'f91bab59-6453-4d1d-8722-f461f71fcf6e','xpacf','2021-12-23 19:03:40'),(546,'e359867f-f27f-4758-8243-0a0f07df53ed','4m24a','2021-12-23 19:06:54'),(548,'66eb53bc-6bf3-4b67-8d1b-2b11429d1684','da2fx','2021-12-23 19:07:01'),(550,'ed51951d-9ec1-40e2-8fd9-382f270f8da7','4w223','2021-12-23 19:07:29'),(551,'ed894cca-1f9a-4253-85c6-abdbb3a69118','7c326','2021-12-23 20:00:10'),(556,'9ad42735-cdcb-43d9-8d57-89a148c61fb4','bpmn3','2021-12-24 17:53:41'),(557,'7370c027-0e81-4553-8dc5-9449f148d256','d7nam','2021-12-25 10:00:33'),(560,'dbfcc46d-0717-45e0-8ad9-7a83b8fa38b2','xmcnc','2021-12-26 10:07:02'),(562,'4078bb21-01c2-4ce9-8acc-c04b9ece1eb8','a242n','2021-12-27 10:11:46'),(563,'f61aa329-6ad5-40a0-84b7-7bb3d102c021','adnad','2021-12-27 10:12:11'),(571,'129ebb47-9c22-4e09-89fa-70d142a9faa5','anybn','2021-12-30 09:28:04'),(572,'65015eae-dee5-483f-8c78-7fc5f6a6aaa5','8x5mc','2021-12-30 09:28:14'),(573,'8fe0cd76-e45d-447a-8965-177f407ac499','g4b4f','2021-12-30 09:28:18'),(581,'e155c04b-7d13-4efc-8055-0425dbafe795','f4afe','2022-01-26 12:32:18');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='系统配置信息表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`param_key`,`param_value`,`status`,`remark`,`create_time`,`update_time`) values (1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息',NULL,NULL),(2,'key','value',1,'rark',NULL,NULL),(9,'key','value',1,'mark',NULL,NULL);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`username`,`operation`,`method`,`params`,`time`,`ip`,`create_date`) values (1,'15613353222','保存用户','com.ddg.meituan.admin.modules.sys.controller.SysUserController.save()','[{\"userId\":17,\"username\":\"15613353221\",\"password\":\"$2a$10$eEJaMUhiBcpGvE9tOKHIpu1fKeQof/kG9hnUmUjfPKXUyN2Q0UIRO\",\"salt\":\"lIiAObyAqMPl4i59cIBc\",\"email\":\"1@qq.com\",\"mobile\":\"15612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":16,\"createTime\":{\"date\":{\"year\":2021,\"month\":11,\"day\":24},\"time\":{\"hour\":17,\"minute\":53,\"second\":50,\"nano\":516000000}}},{\"id\":16,\"username\":\"15613353222\",\"clientId\":\"admin-app\"}]',1227,'127.0.0.1','2021-11-24 17:53:51'),(2,'15613353222','删除角色','com.ddg.meituan.admin.modules.sys.controller.SysRoleController.delete()','[[67,68,69,70]]',342,'127.0.0.1','2021-11-24 18:01:58'),(3,'15613353227','保存配置','com.ddg.meituan.admin.modules.sys.controller.SysConfigController.save()','[{\"id\":2,\"paramKey\":\"key\",\"paramValue\":\"value\",\"remark\":\"rark\"}]',159,'127.0.0.1','2021-12-26 10:45:34'),(4,'15613353227','保存配置','com.ddg.meituan.admin.modules.sys.controller.SysConfigController.save()','[{\"id\":9,\"paramKey\":\"key\",\"paramValue\":\"value\",\"remark\":\"mark\"}]',59,'127.0.0.1','2021-12-26 10:49:33'),(5,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0,\"list\":[]}]',40,'127.0.0.1','2021-12-26 14:39:12'),(6,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0,\"list\":[]}]',27,'127.0.0.1','2021-12-26 14:39:43'),(7,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0,\"list\":[]}]',50,'127.0.0.1','2021-12-26 14:41:27'),(8,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0,\"list\":[]}]',28,'127.0.0.1','2021-12-26 14:42:25'),(9,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0,\"list\":[]}]',28,'127.0.0.1','2021-12-26 14:44:53'),(10,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":45,\"parentId\":0,\"name\":\"用户系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"admin\",\"orderNum\":0,\"list\":[]}]',30,'127.0.0.1','2021-12-26 14:45:54'),(11,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":42,\"parentId\":0,\"name\":\"优惠营销\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"mudedi\",\"orderNum\":0,\"list\":[]}]',34,'127.0.0.1','2021-12-26 14:49:32'),(12,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":0,\"list\":[]}]',36,'127.0.0.1','2021-12-26 14:53:38'),(13,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":42,\"parentId\":0,\"name\":\"优惠营销\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"mudedi\",\"orderNum\":0,\"list\":[]}]',37,'127.0.0.1','2021-12-26 15:03:56'),(14,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"mudedi\",\"orderNum\":0,\"list\":[]}]',41,'127.0.0.1','2021-12-26 15:24:13'),(15,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":0,\"list\":[]}]',31,'127.0.0.1','2021-12-26 15:24:23'),(16,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":0,\"list\":[]}]',44,'127.0.0.1','2021-12-26 15:24:28'),(17,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"menu\",\"orderNum\":0,\"list\":[]}]',67,'127.0.0.1','2021-12-26 15:41:08'),(18,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"tixing\",\"orderNum\":0,\"list\":[]}]',38,'127.0.0.1','2021-12-26 15:41:44'),(19,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]',28,'127.0.0.1','2021-12-26 15:43:02'),(20,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"tubiao\",\"orderNum\":0,\"list\":[]}]',29,'127.0.0.1','2021-12-26 15:43:11'),(21,'15613353227','保存菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.save()','[{\"menuId\":76,\"parentId\":0,\"name\":\"测试\",\"url\":\"test\",\"type\":1,\"icon\":\"sousuo\",\"orderNum\":0,\"list\":[]}]',53,'127.0.0.1','2021-12-26 15:46:40'),(22,'15613353227','删除菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.delete()','[76]',75,'127.0.0.1','2021-12-26 15:46:53'),(23,'15613353227','删除菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.delete()','[76]',55,'127.0.0.1','2021-12-26 15:46:58'),(24,'15613353227','保存菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.save()','[{\"menuId\":77,\"parentId\":0,\"name\":\"asdf\",\"url\":\"sdf\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":0,\"list\":[]}]',41,'127.0.0.1','2021-12-26 15:47:13'),(25,'15613353227','删除菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.delete()','[77]',58,'127.0.0.1','2021-12-26 15:47:19'),(26,'15613353227','删除菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.delete()','[77]',66,'127.0.0.1','2021-12-26 15:48:47'),(27,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":34,\"parentId\":31,\"name\":\"商家管理\",\"url\":\"product/brand\",\"perms\":\"\",\"type\":1,\"icon\":\"editor\",\"orderNum\":0,\"list\":[]}]',61,'127.0.0.1','2021-12-31 11:31:18'),(28,'15613353227','保存菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.save()','[{\"menuId\":78,\"parentId\":31,\"name\":\"地址管理\",\"url\":\"product/address\",\"perms\":\"\",\"type\":1,\"icon\":\"config\",\"orderNum\":0,\"list\":[]}]',51,'127.0.0.1','2021-12-31 12:16:10'),(29,'15613353227','删除菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.delete()','[78]',77,'127.0.0.1','2021-12-31 12:21:32'),(30,'15613353227','保存菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.save()','[{\"menuId\":79,\"parentId\":31,\"name\":\"地址管理\",\"url\":\"product-address\",\"perms\":\"\",\"type\":1,\"icon\":\"shouye\",\"orderNum\":2,\"list\":[]}]',39,'127.0.0.1','2021-12-31 12:26:27'),(31,'15613353227','修改菜单','com.ddg.meituan.admin.modules.sys.controller.SysMenuController.update()','[{\"menuId\":79,\"parentId\":31,\"name\":\"地址管理\",\"url\":\"product/address\",\"perms\":\"\",\"type\":1,\"icon\":\"shouye\",\"orderNum\":2,\"list\":[]}]',44,'127.0.0.1','2021-12-31 12:26:43'),(32,'admin','修改角色','com.ddg.meituan.admin.modules.sys.controller.SysRoleController.update()','[{\"roleId\":64,\"roleName\":\"ADMIN\",\"remark\":\"管理员角色\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,6,7,8,9,10,11,12,13,14,27,29,30,31,32,34,37,38,39,40,41,68,69,73,79,42,47,48,49,50,51,52,74,75,43,53,54,55,70,71,72,44,56,57,58,59,60,45,61,62,63,64,46,65,66,67,-666666]}]',1554,'127.0.0.1','2021-12-31 12:28:39'),(33,'admin','修改角色','com.ddg.meituan.admin.modules.sys.controller.SysRoleController.update()','[{\"roleId\":64,\"roleName\":\"ADMIN\",\"remark\":\"管理员角色\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,6,7,8,9,10,11,12,13,14,27,29,30,31,32,34,37,38,39,40,41,68,69,73,79,42,47,48,49,50,51,52,74,75,43,53,54,55,70,71,72,44,56,57,58,59,60,45,61,62,63,64,46,65,66,67,-666666]}]',1405,'127.0.0.1','2021-12-31 12:28:41');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values (1,0,'系统管理',NULL,NULL,0,'system',0),(2,1,'管理员列表','sys/user',NULL,1,'admin',1),(3,1,'角色管理','sys/role',NULL,1,'role',2),(4,1,'菜单管理','sys/menu',NULL,1,'menu',3),(6,1,'定时任务','job/schedule',NULL,1,'job',5),(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),(15,2,'查看',NULL,'/sys/user/list',2,NULL,0),(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),(20,3,'新增',NULL,'sys:role:save,sys:menu:list',2,NULL,0),(21,3,'修改',NULL,'sys:role:update,sys:menu:list',2,NULL,0),(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),(27,1,'参数管理','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'config',6),(29,1,'系统日志','sys/log','sys:log:list',1,'log',7),(30,1,'文件上传','oss/oss','sys:oss:all',1,'oss',6),(31,0,'商品系统','','',0,'tubiao',0),(32,31,'分类维护','product/category','',1,'menu',0),(34,31,'商家管理','product/brand','',1,'editor',0),(37,31,'平台属性','','',0,'system',0),(38,37,'属性分组','product/attrgroup','',1,'tubiao',0),(39,37,'规格参数','product/baseattr','',1,'log',0),(40,37,'销售属性','product/saleattr','',1,'zonghe',0),(41,31,'商品维护','product/spu','',0,'zonghe',0),(42,0,'优惠营销','','',0,'mudedi',0),(43,0,'库存系统','','',0,'shouye',0),(44,0,'订单系统','','',0,'config',0),(45,0,'用户系统','','',0,'admin',0),(46,0,'内容管理','','',0,'sousuo',0),(47,42,'优惠券管理','coupon/coupon','',1,'zhedie',0),(48,42,'发放记录','coupon/history','',1,'sql',0),(49,42,'专题活动','coupon/subject','',1,'tixing',0),(50,42,'秒杀活动','coupon/seckill','',1,'daohang',0),(51,42,'积分维护','coupon/bounds','',1,'geren',0),(52,42,'满减折扣','coupon/full','',1,'shoucang',0),(53,43,'仓库维护','ware/wareinfo','',1,'shouye',0),(54,43,'库存工作单','ware/task','',1,'log',0),(55,43,'商品库存','ware/sku','',1,'jiesuo',0),(56,44,'订单查询','order/order','',1,'zhedie',0),(57,44,'退货单处理','order/return','',1,'shanchu',0),(58,44,'等级规则','order/settings','',1,'system',0),(59,44,'支付流水查询','order/payment','',1,'job',0),(60,44,'退款流水查询','order/refund','',1,'mudedi',0),(61,45,'会员列表','member/member','',1,'geren',0),(62,45,'会员等级','member/level','',1,'tubiao',0),(63,45,'积分变化','member/growth','',1,'bianji',0),(64,45,'统计信息','member/statistics','',1,'sql',0),(65,46,'首页推荐','content/index','',1,'shouye',0),(66,46,'分类热门','content/category','',1,'zhedie',0),(67,46,'评论管理','content/comments','',1,'pinglun',0),(68,41,'spu管理','product/spu','',1,'config',0),(69,41,'发布商品','product/spuadd','',1,'bianji',0),(70,43,'采购单维护','','',0,'tubiao',0),(71,70,'采购需求','ware/purchaseitem','',1,'editor',0),(72,70,'采购单','ware/purchase','',1,'menu',0),(73,41,'商品管理','product/manager','',1,'zonghe',0),(74,42,'会员价格','coupon/memberprice','',1,'admin',0),(75,42,'每日秒杀','coupon/seckillsession','',1,'job',0),(79,31,'地址管理','product/address','',1,'shouye',2);

/*Table structure for table `sys_oss` */

DROP TABLE IF EXISTS `sys_oss`;

CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件上传';

/*Data for the table `sys_oss` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`remark`,`create_user_id`,`create_time`) values (64,'ADMIN','管理员角色',1,'2021-11-22 17:00:03'),(65,'USER','普通用户',2,'2021-11-23 17:02:37'),(66,'USER2','156 创建',13,'2021-11-24 13:27:34');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=738 DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values (1,9,2),(2,9,15),(3,9,16),(4,9,17),(5,9,18),(6,9,-666666),(7,9,1),(8,10,2),(9,10,15),(10,10,16),(11,10,17),(12,10,18),(13,10,-666666),(14,10,1),(15,11,1),(16,11,2),(17,11,15),(18,11,16),(19,11,17),(20,11,18),(21,11,3),(22,11,19),(23,11,20),(24,11,21),(25,11,22),(26,11,4),(27,11,23),(28,11,24),(29,11,25),(30,11,26),(31,11,5),(32,11,6),(33,11,7),(34,11,8),(35,11,9),(36,11,10),(37,11,11),(38,11,12),(39,11,13),(40,11,14),(41,11,27),(42,11,29),(43,11,30),(44,11,-666666),(45,12,1),(46,12,2),(47,12,15),(48,12,16),(49,12,17),(50,12,18),(51,12,3),(52,12,19),(53,12,20),(54,12,21),(55,12,22),(56,12,4),(57,12,23),(58,12,24),(59,12,25),(60,12,26),(61,12,5),(62,12,6),(63,12,7),(64,12,8),(65,12,9),(66,12,10),(67,12,11),(68,12,12),(69,12,13),(70,12,14),(71,12,27),(72,12,29),(73,12,30),(74,12,-666666),(75,13,1),(76,13,2),(77,13,15),(78,13,16),(79,13,17),(80,13,18),(81,13,3),(82,13,19),(83,13,20),(84,13,21),(85,13,22),(86,13,4),(87,13,23),(88,13,24),(89,13,25),(90,13,26),(91,13,5),(92,13,6),(93,13,7),(94,13,8),(95,13,9),(96,13,10),(97,13,11),(98,13,12),(99,13,13),(100,13,14),(101,13,27),(102,13,29),(103,13,30),(104,13,-666666),(105,14,1),(106,14,2),(107,14,15),(108,14,16),(109,14,17),(110,14,18),(111,14,3),(112,14,19),(113,14,20),(114,14,21),(115,14,22),(116,14,4),(117,14,23),(118,14,24),(119,14,25),(120,14,26),(121,14,5),(122,14,6),(123,14,7),(124,14,8),(125,14,9),(126,14,10),(127,14,11),(128,14,12),(129,14,13),(130,14,14),(131,14,27),(132,14,29),(133,14,30),(134,14,-666666),(135,15,1),(136,15,2),(137,15,15),(138,15,16),(139,15,17),(140,15,18),(141,15,3),(142,15,19),(143,15,20),(144,15,21),(145,15,22),(146,15,4),(147,15,23),(148,15,24),(149,15,25),(150,15,26),(151,15,5),(152,15,6),(153,15,7),(154,15,8),(155,15,9),(156,15,10),(157,15,11),(158,15,12),(159,15,13),(160,15,14),(161,15,27),(162,15,29),(163,15,30),(164,15,-666666),(165,16,1),(166,16,2),(167,16,15),(168,16,16),(169,16,17),(170,16,18),(171,16,3),(172,16,19),(173,16,20),(174,16,21),(175,16,22),(176,16,4),(177,16,23),(178,16,24),(179,16,25),(180,16,26),(181,16,5),(182,16,6),(183,16,7),(184,16,8),(185,16,9),(186,16,10),(187,16,11),(188,16,12),(189,16,13),(190,16,14),(191,16,27),(192,16,29),(193,16,30),(194,16,-666666),(195,17,29),(196,17,-666666),(197,17,1),(198,18,29),(199,18,-666666),(200,18,1),(201,19,15),(202,19,-666666),(203,19,1),(204,19,2),(205,20,15),(206,20,-666666),(207,20,1),(208,20,2),(209,21,15),(210,21,-666666),(211,21,1),(212,21,2),(213,22,15),(214,22,-666666),(215,22,1),(216,22,2),(217,23,15),(218,23,-666666),(219,23,1),(220,23,2),(221,24,15),(222,24,-666666),(223,24,1),(224,24,2),(225,25,8),(226,25,-666666),(227,25,1),(228,25,6),(229,26,8),(230,26,-666666),(231,26,1),(232,26,6),(233,27,15),(234,27,-666666),(235,27,1),(236,27,2),(237,28,15),(238,28,-666666),(239,28,1),(240,28,2),(241,29,15),(242,29,-666666),(243,29,1),(244,29,2),(245,30,15),(246,30,-666666),(247,30,1),(248,30,2),(249,31,15),(250,31,-666666),(251,31,1),(252,31,2),(253,32,15),(254,32,-666666),(255,32,1),(256,32,2),(257,33,15),(258,33,-666666),(259,33,1),(260,33,2),(261,34,15),(262,34,-666666),(263,34,1),(264,34,2),(265,35,15),(266,35,-666666),(267,35,1),(268,35,2),(269,36,15),(270,36,-666666),(271,36,1),(272,36,2),(273,37,15),(274,37,-666666),(275,37,1),(276,37,2),(277,38,15),(278,38,-666666),(279,38,1),(280,38,2),(281,39,15),(282,39,-666666),(283,39,1),(284,39,2),(285,40,15),(286,40,-666666),(287,40,1),(288,40,2),(289,41,15),(290,41,-666666),(291,41,1),(292,41,2),(293,42,15),(294,42,-666666),(295,42,1),(296,42,2),(297,43,15),(298,43,-666666),(299,43,1),(300,43,2),(301,44,15),(302,44,-666666),(303,44,1),(304,44,2),(305,45,15),(306,45,-666666),(307,45,1),(308,45,2),(309,46,15),(310,46,-666666),(311,46,1),(312,46,2),(313,47,15),(314,47,-666666),(315,47,1),(316,47,2),(317,48,15),(318,48,-666666),(319,48,1),(320,48,2),(321,49,15),(322,49,-666666),(323,49,1),(324,49,2),(325,50,15),(326,50,-666666),(327,50,1),(328,50,2),(329,51,15),(330,51,-666666),(331,51,1),(332,51,2),(333,52,15),(334,52,-666666),(335,52,1),(336,52,2),(555,65,45),(556,65,61),(557,65,62),(558,65,63),(559,65,64),(560,65,-666666),(561,66,1),(562,66,2),(563,66,15),(564,66,16),(565,66,17),(566,66,18),(567,66,3),(568,66,19),(569,66,20),(570,66,21),(571,66,22),(572,66,4),(573,66,23),(574,66,24),(575,66,25),(576,66,26),(577,66,6),(578,66,7),(579,66,8),(580,66,9),(581,66,10),(582,66,11),(583,66,12),(584,66,13),(585,66,14),(586,66,27),(587,66,29),(588,66,30),(589,66,-666666),(666,64,1),(667,64,2),(668,64,15),(669,64,16),(670,64,17),(671,64,18),(672,64,3),(673,64,19),(674,64,20),(675,64,21),(676,64,22),(677,64,4),(678,64,23),(679,64,24),(680,64,25),(681,64,26),(682,64,6),(683,64,7),(684,64,8),(685,64,9),(686,64,10),(687,64,11),(688,64,12),(689,64,13),(690,64,14),(691,64,27),(692,64,29),(693,64,30),(694,64,31),(695,64,32),(696,64,34),(697,64,37),(698,64,38),(699,64,39),(700,64,40),(701,64,41),(702,64,68),(703,64,69),(704,64,73),(705,64,79),(706,64,42),(707,64,47),(708,64,48),(709,64,49),(710,64,50),(711,64,51),(712,64,52),(713,64,74),(714,64,75),(715,64,43),(716,64,53),(717,64,54),(718,64,55),(719,64,70),(720,64,71),(721,64,72),(722,64,44),(723,64,56),(724,64,57),(725,64,58),(726,64,59),(727,64,60),(728,64,45),(729,64,61),(730,64,62),(731,64,63),(732,64,64),(733,64,46),(734,64,65),(735,64,66),(736,64,67),(737,64,-666666);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`salt`,`email`,`mobile`,`status`,`create_user_id`,`create_time`) values (1,'admin','$2a$10$3fLis.YTXM1CoNzWMvpS2us0NQ1EBk8sq0081X8oVFtIXuZI0dRMG','YzcmCZNvbXocrsz9dm8e','root@renren.io','13612345678',1,1,'2016-11-11 11:11:11'),(13,'15613353227','$2a$10$9hsjWBS7VShfc6aMVtAnuef1FClknFe4h/H0QkYCDs8FW7nRjCsOO','4E7HLmCB9b0H7eXqWDWC','1@qq.com','15613353227',1,1,'2021-11-23 17:25:44'),(16,'15613353222','$2a$10$Qbm2Cuwen07PQBe.Lu3JNeme5UA7ZyIW3gMv1sib13d7PIkD4HCXS','85oSGe9zPdSEGZ7Lm5R5','1@qq.com','15613332222',1,13,'2021-11-24 13:28:19'),(17,'15613353221','$2a$10$eEJaMUhiBcpGvE9tOKHIpu1fKeQof/kG9hnUmUjfPKXUyN2Q0UIRO','lIiAObyAqMPl4i59cIBc','1@qq.com','15612345678',1,16,'2021-11-24 17:53:51');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (1,7,64),(2,8,64),(3,9,64),(4,10,64),(7,2,65),(12,1,64),(13,13,64),(14,13,65),(15,16,66);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

/*Data for the table `tb_user` */

insert  into `tb_user`(`user_id`,`username`,`mobile`,`password`,`create_time`) values (1,'mark','13612345678','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','2017-03-23 22:37:41');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
