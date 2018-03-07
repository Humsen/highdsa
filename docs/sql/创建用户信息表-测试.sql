/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : highdsa

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 2018-03-07 15:47:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_create_date` timestamp(3) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info`(user_name, user_password, user_create_date) VALUES ('何明胜', '123123', NOW());
INSERT INTO `user_info`(user_name, user_password, user_create_date) VALUES ('龙傲天', '123123', NOW());

SET FOREIGN_KEY_CHECKS = 1;