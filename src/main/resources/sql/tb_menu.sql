/*
Navicat MySQL Data Transfer

Source Server         : szy
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : jason_cms

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-27 15:13:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
