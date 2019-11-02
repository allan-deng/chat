/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : chatserver

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 02/11/2019 22:30:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chatinfo
-- ----------------------------
DROP TABLE IF EXISTS `chatinfo`;
CREATE TABLE `chatinfo`  (
  `NO` int(255) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `SENDUID` int(11) NOT NULL COMMENT '发送用户号',
  `RECEIVEUID` int(11) NOT NULL COMMENT '接受用户号',
  `TEXT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  PRIMARY KEY (`NO`) USING BTREE,
  INDEX `SID`(`SENDUID`) USING BTREE,
  INDEX `RID`(`RECEIVEUID`) USING BTREE,
  CONSTRAINT `SID` FOREIGN KEY (`SENDUID`) REFERENCES `userinfo` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `RID` FOREIGN KEY (`RECEIVEUID`) REFERENCES `userinfo` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `NO` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UID` int(11) NOT NULL,
  `FRIENDUID` int(11) NOT NULL,
  `DATE` timestamp NULL DEFAULT NULL COMMENT '关系创建时间',
  PRIMARY KEY (`NO`) USING BTREE,
  INDEX `FUID`(`UID`) USING BTREE,
  CONSTRAINT `FUID` FOREIGN KEY (`UID`) REFERENCES `userinfo` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `NO` int(11) NOT NULL AUTO_INCREMENT,
  `UID` int(11) NOT NULL,
  `IP` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PORT` int(11) NULL DEFAULT NULL,
  `DATE` timestamp NULL DEFAULT NULL,
  `STATUS` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`NO`) USING BTREE,
  INDEX `USERID`(`UID`) USING BTREE,
  CONSTRAINT `USERID` FOREIGN KEY (`UID`) REFERENCES `userinfo` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pwdfind
-- ----------------------------
DROP TABLE IF EXISTS `pwdfind`;
CREATE TABLE `pwdfind`  (
  `NO` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UID` int(11) NOT NULL,
  `Q1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `A1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Q2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `A2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Q3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `A3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`NO`) USING BTREE,
  INDEX `PUID`(`UID`) USING BTREE,
  CONSTRAINT `PUID` FOREIGN KEY (`UID`) REFERENCES `userinfo` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `UID` int(11) NOT NULL,
  `PWD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SIG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IMAGEID` int(11) NULL DEFAULT NULL,
  `NICKNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SEX` tinyint(1) NULL DEFAULT NULL,
  `BIRTHDAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TEL` int(11) NULL DEFAULT NULL,
  `EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ADDRESS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (666, 'ddd', 'aaa', NULL, 'aaa', 1, 'aaa', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
