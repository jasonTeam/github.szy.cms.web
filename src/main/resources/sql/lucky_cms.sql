/*
Navicat MySQL Data Transfer

Source Server         : szy
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : lucky_cms

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-01-21 10:49:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `is_delete` smallint(4) DEFAULT '1' COMMENT '是否删除 1：正常，-1：已删除',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1516456578456032 DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '人人开源集团', '0', '1');
INSERT INTO `sys_dept` VALUES ('2', '1', '长沙分公司', '1', '1');
INSERT INTO `sys_dept` VALUES ('3', '1', '上海分公司', '2', '1');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '1');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '1');
INSERT INTO `sys_dept` VALUES ('1516456578456031', '3', '人事部', '2', '1');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='部门角色关联表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------
INSERT INTO `sys_dept_role` VALUES ('1', null, '1516456578456031');
INSERT INTO `sys_dept_role` VALUES ('2', '6', '1516456578456031');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='菜单菜单信息表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'sys/dept.html', null, '1', 'fa fa-file-code-o', '1');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '0', '运营管理', null, null, '0', 'fa fa-cog', '1');
INSERT INTO `sys_menu` VALUES ('37', '0', '直播管理', null, null, '0', 'fa fa-cog', '2');
INSERT INTO `sys_menu` VALUES ('38', '0', '人事管理', null, null, '0', 'fa fa-cog', '3');
INSERT INTO `sys_menu` VALUES ('39', '0', '财务管理', null, null, '0', 'fa fa-cog', '4');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='菜单角色关联表';

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('12', '1', '1');
INSERT INTO `sys_menu_role` VALUES ('13', '1', '2');
INSERT INTO `sys_menu_role` VALUES ('14', '1', '15');
INSERT INTO `sys_menu_role` VALUES ('15', '1', '6');
INSERT INTO `sys_menu_role` VALUES ('16', '1', '7');
INSERT INTO `sys_menu_role` VALUES ('17', '1', '8');
INSERT INTO `sys_menu_role` VALUES ('18', '1', '14');
INSERT INTO `sys_menu_role` VALUES ('19', '1', '28');
INSERT INTO `sys_menu_role` VALUES ('20', null, '1');
INSERT INTO `sys_menu_role` VALUES ('21', null, '31');
INSERT INTO `sys_menu_role` VALUES ('22', null, '32');
INSERT INTO `sys_menu_role` VALUES ('23', null, '33');
INSERT INTO `sys_menu_role` VALUES ('24', null, '34');
INSERT INTO `sys_menu_role` VALUES ('25', null, '35');
INSERT INTO `sys_menu_role` VALUES ('26', '6', '1');
INSERT INTO `sys_menu_role` VALUES ('27', '6', '31');
INSERT INTO `sys_menu_role` VALUES ('28', '6', '32');
INSERT INTO `sys_menu_role` VALUES ('29', '6', '33');
INSERT INTO `sys_menu_role` VALUES ('30', '6', '34');
INSERT INTO `sys_menu_role` VALUES ('31', '6', '35');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `is_delete` smallint(4) DEFAULT '1' COMMENT '是否删除(1：正常，-1：删除)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '4', '开发', '2018-01-04 15:01:44', null, '这个一个测试', '1');
INSERT INTO `sys_role` VALUES ('2', '5', '销售专员', '2018-01-17 14:59:46', null, 'sell Top', '1');
INSERT INTO `sys_role` VALUES ('3', '4', '技术总监', '2018-01-19 16:01:26', null, '他是技术总监', '1');
INSERT INTO `sys_role` VALUES ('5', '1516456578456031', '人事', null, null, null, '1');
INSERT INTO `sys_role` VALUES ('6', '1516456578456031', '人事助理', '2018-01-21 10:31:28', null, '人事助理小MM', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` smallint(6) DEFAULT '1' COMMENT '是否删除:（1：未删除，-1：删除）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1516456578443912 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'YzcmCZNvbXocrsz9dm8e', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '13612345678', 'root@renren.io', '1', '2016-11-11 11:11:11', '1');
INSERT INTO `sys_user` VALUES ('2', '4', 'jason', 'd7GdTeUuoGGxuDoqTq2u', 'df40cc739038c75fb9d2ebe617fa5f05b8bf5be0341bf533c61eaf037a58d060', '15267151846', '2350327011@qq.com', '0', '2018-01-04 15:06:28', '1');
INSERT INTO `sys_user` VALUES ('1516456578443911', '5', 'miffy', '8ipvpbhlmHwyUBJZH27H', '6beb4ba7a6b1ecd06257dbbfdba38bd72b4166d691fcebdd0b9dc84fd59890bd', '15267151846', '2350327011@qq.com', '1', '2018-01-20 22:18:01', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1', '2018-01-18');
INSERT INTO `sys_user_role` VALUES ('2', '1516456578443911', '3', '2018-01-20');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_id` bigint(50) NOT NULL,
  `menu_name` varchar(30) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT NULL,
  `parent_id` bigint(50) DEFAULT NULL,
  `sort` int(4) DEFAULT NULL,
  `ctime` int(20) DEFAULT NULL,
  `utime` int(20) DEFAULT NULL,
  `is_delete` smallint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(50) NOT NULL,
  `user_name` varchar(25) DEFAULT NULL,
  `user_pwd` varchar(31) DEFAULT NULL,
  `role_id` int(30) DEFAULT NULL COMMENT '角色ID',
  `user_status` smallint(2) DEFAULT '1' COMMENT '用户状态(1.正常；-1.冻结)',
  `login_ip` varchar(255) DEFAULT NULL,
  `ctime` int(11) DEFAULT NULL,
  `utime` int(11) DEFAULT NULL,
  `is_delete` smallint(6) DEFAULT '1' COMMENT '是否删除(1.正常，-1.删除)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'jason', '123456', '1', '1', '', null, null, '1');
