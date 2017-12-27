/*
Navicat MySQL Data Transfer

Source Server         : szy
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : jason_cms

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-27 15:13:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(50) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) DEFAULT NULL,
  `user_pwd` varchar(31) DEFAULT NULL,
  `role_id` int(30) DEFAULT NULL COMMENT '角色ID',
  `user_status` smallint(2) DEFAULT '1' COMMENT '用户状态(1.正常；-1.冻结)',
  `login_ip` varchar(255) DEFAULT NULL,
  `ctime` int(11) DEFAULT NULL,
  `utime` int(11) DEFAULT NULL,
  `is_delete` smallint(6) DEFAULT '1' COMMENT '是否删除(1.正常，-1.删除)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'jason', '123456', '1', '1', '', null, null, '1');
