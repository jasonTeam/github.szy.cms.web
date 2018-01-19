/*
Navicat MySQL Data Transfer

Source Server         : szy
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : lucky_cms

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-01-19 18:13:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '人人开源集团', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '长沙分公司', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '上海分公司', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '0');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门角色关联表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
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
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员管理', 'sys/user.html', null, '1', 'fa fa-user', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='菜单角色关联表';

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

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '开发', '这个一个测试', '4', '2018-01-04 15:01:44');
INSERT INTO `sys_role` VALUES ('2', '销售专员', 'sell Top', '5', '2018-01-17 14:59:46');
INSERT INTO `sys_role` VALUES ('3', '技术总监', '他是技术总监', '4', '2018-01-19 16:01:26');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` smallint(6) DEFAULT '1' COMMENT '是否删除:（1：未删除，-1：删除）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1516328306906804 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11', '1');
INSERT INTO `sys_user` VALUES ('2', 'jason', 'df40cc739038c75fb9d2ebe617fa5f05b8bf5be0341bf533c61eaf037a58d060', 'd7GdTeUuoGGxuDoqTq2u', '2350327011@qq.com', '15267151846', '0', '4', '2018-01-04 15:06:28', '1');
INSERT INTO `sys_user` VALUES ('3', 'Andy', '02dc7d844d5294d317b824feb4f31d3f22deb963c80c223f7b12006a96b1dcc7', 'wyD5P3UOSnpTHGmftAGy', '2350327011@qq.com', '15267151849', '1', '5', '2018-01-17 14:58:03', '1');
INSERT INTO `sys_user` VALUES ('1516327556268878', 'jack', null, 'm5DicU3QAMnnpt2gDvYb', '2350327011@qq.com', '15267151846', '1', '1', '2018-01-19 10:10:33', '1');
INSERT INTO `sys_user` VALUES ('1516328306906797', 'afdda', '3f32f24c745b5bd1b916d83b89b8b5e8e71dd187b8bfb16f5e34d92cf4183689', 'IXT7AAM5WdiA4MH8qOQM', 'adf', 'adf', '1', '1', '2018-01-19 10:20:21', '1');
INSERT INTO `sys_user` VALUES ('1516328306906798', 'adfa', '96356cdd314711222cdd7284ae80c9bb95cb6983a0fc38acee1ef24667e37be7', 'Y3WLPyrTgGa6oSJ7XbJ5', 'asdfasf', 'afdadsfdasf', '1', '1', '2018-01-19 10:20:29', '1');
INSERT INTO `sys_user` VALUES ('1516328306906799', '12eqe', '93bc288debb958380492163068f3cdc75e89b2217dbee97c51ea6f133c9f581f', '8rrkxbQdFsHhywHwRBYG', 'qewq', 'qeqw', '1', '1', '2018-01-19 10:20:39', '1');
INSERT INTO `sys_user` VALUES ('1516328306906800', 'qeqweqw', 'c8fa17764053af9c87b2748f2b13c6cb6722f0fcf26a97f1f1c63a2860684d4f', 'JNvkqw6GvFnqUwmsMMMH', 'qweq', 'qweqweqwe', '1', '1', '2018-01-19 10:20:46', '1');
INSERT INTO `sys_user` VALUES ('1516328306906801', 'fasfdqeqweqwe', '917b4b8ad860f444ed4ce49167e2bd0aadaf07adbc55bae79e71b8849ad435e5', 'Pc7ozc4odMAtqwvPlykq', 'qeqweqwe', 'qeqewwqe', '1', '1', '2018-01-19 10:20:56', '1');
INSERT INTO `sys_user` VALUES ('1516328306906802', 'qeqwdasdas', 'ae94191dd5332171b1ab3ae81d33284787227a703c83e1c827440ea5206d89ff', 'r9u3lPypTOFQGmKXW4Uq', 'qewqeqw', 'qweqwe', '1', '1', '2018-01-19 10:21:04', '1');
INSERT INTO `sys_user` VALUES ('1516328306906803', 'weqeqwe', 'ae94191dd5332171b1ab3ae81d33284787227a703c83e1c827440ea5206d89ff', 'teb6ZeormAKtWACftbEZ', 'qewqwewqe', 'qeqweqwe', '1', '1', '2018-01-19 10:21:15', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1');

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
