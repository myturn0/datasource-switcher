/*
 Navicat Premium Data Transfer

 Source Server         : MySQLDatabase
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.01:3306
 Source Schema         : switcher_mysql

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 25/02/2019 16:49:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_province
-- ----------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pinyin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_province
-- ----------------------------
BEGIN;
INSERT INTO `t_province` VALUES (1, '北京', 'beijing');
INSERT INTO `t_province` VALUES (2, '河北', 'hebei');
INSERT INTO `t_province` VALUES (3, '重庆', 'chongqing');
INSERT INTO `t_province` VALUES (4, '广东', 'guangdong');
INSERT INTO `t_province` VALUES (5, '新疆', 'xinjiang');
INSERT INTO `t_province` VALUES (6, '云南', 'yunnan');
INSERT INTO `t_province` VALUES (7, '青海', 'qinghai');
INSERT INTO `t_province` VALUES (8, '山东', 'shandong');
INSERT INTO `t_province` VALUES (9, '内蒙古', 'neimenggu');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
