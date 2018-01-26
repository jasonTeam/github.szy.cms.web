/*
Navicat MySQL Data Transfer

Source Server         : szy
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : lucky_cms

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-01-26 16:02:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for anchor_info
-- ----------------------------
DROP TABLE IF EXISTS `anchor_info`;
CREATE TABLE `anchor_info` (
  `id` tinyint(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(50) DEFAULT NULL,
  `anchor_sex` smallint(2) DEFAULT '0' COMMENT '0.男，1.女',
  `mobile` varchar(50) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='主播信息表';

-- ----------------------------
-- Records of anchor_info
-- ----------------------------
INSERT INTO `anchor_info` VALUES ('1', '小花猫', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('2', '大花猫', '1', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('3', '大花猫1', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('4', '大花猫2', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('5', '大花猫3', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('6', '大花猫2', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('7', '大花猫4', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('8', '大花猫-', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('9', '大花猫3', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('10', '大花猫6', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('11', '大花猫2', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('12', '大花猫为', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('13', '大花猫2', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('14', '大花猫3', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('15', 'DSADS', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('16', 'ADAS', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('17', 'ASD', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('18', 'ASD', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('19', 'EWQ', '0', '15267151846', '这是一个好主播');
INSERT INTO `anchor_info` VALUES ('20', 'FDGDSF', '0', '15267151846', '这是一个好主播');

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
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1001', '0', '杭州育体科技', '0', '1');
INSERT INTO `sys_dept` VALUES ('1002', '1001', '技术部', '1', '1');
INSERT INTO `sys_dept` VALUES ('1003', '1001', '运营部', '2', '1');
INSERT INTO `sys_dept` VALUES ('1004', '1001', '产品部', '3', '1');
INSERT INTO `sys_dept` VALUES ('1005', '1001', '行政部', '4', '1');
INSERT INTO `sys_dept` VALUES ('1006', '1001', '财务部', '5', '1');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='部门角色关联表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------
INSERT INTO `sys_dept_role` VALUES ('4', '5', '1002');
INSERT INTO `sys_dept_role` VALUES ('5', '6', '1002');
INSERT INTO `sys_dept_role` VALUES ('6', '7', '1004');
INSERT INTO `sys_dept_role` VALUES ('7', '8', '1005');
INSERT INTO `sys_dept_role` VALUES ('8', '9', '1003');

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
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', 'fa fa-search', '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', 'fa fa-floppy-o', '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', 'fa fa-pencil-square-o', '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', 'fa fa-trash-o', '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', 'fa fa-search', '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', 'fa fa-floppy-o', '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', 'fa fa-pencil-square-o', '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', 'fa fa-trash-o', '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', 'fa fa-search', '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', 'fa fa-floppy-o', '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', 'fa fa-pencil-square-o', '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', 'fa fa-trash-o', '0');
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'sys/dept.html', null, '1', 'fa fa-sitemap', '1');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list,sys:dept:info', '2', 'fa fa-search', '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', 'fa fa-floppy-o', '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', 'fa fa-pencil-square-o', '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', 'fa fa-trash-o', '0');
INSERT INTO `sys_menu` VALUES ('36', '0', '直播管理', null, null, '0', 'fa fa-cog', '1');
INSERT INTO `sys_menu` VALUES ('37', '0', '人事管理', null, null, '0', 'fa fa-cog', '2');
INSERT INTO `sys_menu` VALUES ('38', '36', '主播管理', 'anchor/info.html', '', '1', 'fa fa-cog', '1');
INSERT INTO `sys_menu` VALUES ('39', '38', '查看', null, 'anchor:info:list', '2', null, null);

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='菜单角色关联表';

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('42', '5', '1');
INSERT INTO `sys_menu_role` VALUES ('43', '5', '31');
INSERT INTO `sys_menu_role` VALUES ('44', '5', '32');
INSERT INTO `sys_menu_role` VALUES ('45', '5', '33');
INSERT INTO `sys_menu_role` VALUES ('46', '6', '1');
INSERT INTO `sys_menu_role` VALUES ('47', '6', '31');
INSERT INTO `sys_menu_role` VALUES ('48', '6', '34');
INSERT INTO `sys_menu_role` VALUES ('49', '6', '35');
INSERT INTO `sys_menu_role` VALUES ('50', '7', '1');
INSERT INTO `sys_menu_role` VALUES ('51', '7', '2');
INSERT INTO `sys_menu_role` VALUES ('52', '7', '17');
INSERT INTO `sys_menu_role` VALUES ('53', '8', '1');
INSERT INTO `sys_menu_role` VALUES ('54', '8', '2');
INSERT INTO `sys_menu_role` VALUES ('55', '8', '16');
INSERT INTO `sys_menu_role` VALUES ('56', '8', '17');
INSERT INTO `sys_menu_role` VALUES ('57', '8', '18');
INSERT INTO `sys_menu_role` VALUES ('58', '9', '36');
INSERT INTO `sys_menu_role` VALUES ('59', '9', '38');
INSERT INTO `sys_menu_role` VALUES ('60', '9', '39');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `is_delete` smallint(4) DEFAULT '1' COMMENT '是否删除(1：正常，-1：删除)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('5', '1002', 'ios开发', '2018-01-25 16:27:23', null, null, '1');
INSERT INTO `sys_role` VALUES ('6', '1002', 'java开发', '2018-01-25 16:27:57', null, '后台开发', '1');
INSERT INTO `sys_role` VALUES ('7', '1004', 'test', '2018-01-25 16:29:01', null, '产品大神', '1');
INSERT INTO `sys_role` VALUES ('8', '1005', '人事', '2018-01-25 16:29:34', null, '人事', '1');
INSERT INTO `sys_role` VALUES ('9', '1003', '运营专员', '2018-01-26 15:42:29', null, '针对主播的运营和推广', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1001', 'superAdmin', 'YzcmCZNvbXocrsz9dm8e', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', '13612345678', 'longmao@com', '1', '2016-11-11 11:11:11', '1');
INSERT INTO `sys_user` VALUES ('1002', '1002', '伟飞', 'KDFknsp0oWG5aq88kcuA', 'ac21f32ed126c023a4a1f914795ff4dc043b774daa104e606abe2a88f1360982', '15267151846', '2350327011@qq.com', '1', '2018-01-25 16:32:08', '1');
INSERT INTO `sys_user` VALUES ('1003', '1004', '大花', 'yrMQmF6KeFGZprDJZuKz', '063221056d896f15e82b80c024731510f174de92ab0003647622a90f11dc10e2', '15267151846', '2350327011@qq.com', '1', '2018-01-25 16:33:01', '1');
INSERT INTO `sys_user` VALUES ('1004', '1005', '三毛', 'Crq5GTsi61hCJdA6PZRG', '8bfb7920e8703b066e5554cee6c8fc961d6200f2ec8b766e7fbc8d4c84fa124b', '15267151846', '2350327011@qq.com', '1', '2018-01-25 16:33:30', '1');
INSERT INTO `sys_user` VALUES ('1005', '1003', 'anchor', 'SiVJry7OQu9Nog4AvduH', '8caae622d69a04f0055ff9f84cb632b60c34527ade5dd4bb1d0b85b0fd6c4380', '15267151846', '2350327011@qq.com', '1', '2018-01-26 15:43:01', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('6', '1001', '5', '2018-01-25');
INSERT INTO `sys_user_role` VALUES ('7', '1002', '6', '2018-01-25');
INSERT INTO `sys_user_role` VALUES ('8', '1003', '7', '2018-01-25');
INSERT INTO `sys_user_role` VALUES ('9', '1004', '8', '2018-01-25');
INSERT INTO `sys_user_role` VALUES ('10', '1005', '9', '2018-01-26');
s