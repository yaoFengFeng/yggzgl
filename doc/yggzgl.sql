/*
 Navicat Premium Data Transfer

 Source Server         : ayf
 Source Server Type    : MySQL
 Source Server Version : 100136
 Source Host           : localhost:3306
 Source Schema         : yggzgl

 Target Server Type    : MySQL
 Target Server Version : 100136
 File Encoding         : 65001

 Date: 29/05/2019 18:59:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '10014', '123456', '安耀锋');

-- ----------------------------
-- Table structure for all_wage
-- ----------------------------
DROP TABLE IF EXISTS `all_wage`;
CREATE TABLE `all_wage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '工号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `basic_salary` float(10, 2) DEFAULT NULL COMMENT '基本工资与职位 对应',
  `bonus` float(10, 2) DEFAULT NULL COMMENT '奖金',
  `basic_years_salary` float(10, 2) DEFAULT NULL COMMENT '工龄工资 工龄*工作时间',
  `others` float(10, 2) DEFAULT NULL COMMENT '其他',
  `count` float(10, 2) DEFAULT NULL COMMENT '总计',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of all_wage
-- ----------------------------
INSERT INTO `all_wage` VALUES (1, '2019-04', '技术部', '10014', '安耀锋', '工程师', 4000.00, 800.00, 0.00, NULL, 4800.00, NULL);
INSERT INTO `all_wage` VALUES (2, '2019-04', '人事部', '10016', '张三', '职工', 2000.00, 500.00, 0.00, NULL, 2500.00, NULL);
INSERT INTO `all_wage` VALUES (3, '2019-04', '技术部', '10020', '小邓', '助理', 2000.00, 500.00, 0.00, NULL, 2500.00, NULL);
INSERT INTO `all_wage` VALUES (4, '2019-04', '人事部', '10021', '谢逊', '助理', 2000.00, 500.00, 0.00, NULL, 2500.00, NULL);
INSERT INTO `all_wage` VALUES (5, '2019-04', '董事局', '10024', '周芷若', '总经理', 8000.00, 2000.00, 0.00, NULL, 10000.00, NULL);
INSERT INTO `all_wage` VALUES (6, '2019-04', '技术部', '10025', '张无忌', '经理', 5000.00, 1000.00, 0.00, NULL, 6000.00, NULL);
INSERT INTO `all_wage` VALUES (7, '2019-04', '人事部', '10026', '赵敏', '工程师', 6000.00, 500.00, 0.00, NULL, 6500.00, NULL);
INSERT INTO `all_wage` VALUES (8, '2019-04', '技术部', '10027', '蒋佩凯', '实习生', 3000.00, 500.00, 0.00, NULL, 3500.00, NULL);
INSERT INTO `all_wage` VALUES (17, '2019-05', '技术部', '10014', '安耀锋', '工程师', 4000.00, 800.00, 0.00, 200.00, 5000.00, '高温费200');
INSERT INTO `all_wage` VALUES (18, '2019-05', '人事部', '10016', '张三', '职工', 2000.00, 0.00, 0.00, 200.00, 2200.00, '高温费200');
INSERT INTO `all_wage` VALUES (19, '2019-05', '技术部', '10020', '小邓', '助理', 2000.00, 500.00, 0.00, 200.00, 2700.00, '高温费200');
INSERT INTO `all_wage` VALUES (20, '2019-05', '人事部', '10021', '谢逊', '助理', 2000.00, 0.00, 0.00, 200.00, 2200.00, '高温费200');
INSERT INTO `all_wage` VALUES (21, '2019-05', '董事局', '10024', '周芷若', '总经理', 8000.00, 2000.00, 0.00, -100.00, 9900.00, '高温费200，打游戏-300');
INSERT INTO `all_wage` VALUES (22, '2019-05', '技术部', '10025', '张无忌', '经理', 5000.00, 1000.00, 0.00, 200.00, 6200.00, '高温费200');
INSERT INTO `all_wage` VALUES (23, '2019-05', '财务部', '10026', '赵敏', '工程师', 5000.00, 1000.00, 0.00, 200.00, 6200.00, '高温费200');
INSERT INTO `all_wage` VALUES (24, '2019-05', '技术部', '10027', '蒋佩凯', '实习生', 3000.00, 500.00, 0.00, 200.00, 3700.00, '高温费200');

-- ----------------------------
-- Table structure for depadmin
-- ----------------------------
DROP TABLE IF EXISTS `depadmin`;
CREATE TABLE `depadmin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `depment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of depadmin
-- ----------------------------
INSERT INTO `depadmin` VALUES (1, '10016', '张三', '123456', '人事部');
INSERT INTO `depadmin` VALUES (2, '10020', '小邓', '123456', '技术部');
INSERT INTO `depadmin` VALUES (3, '10024', '周芷若', '123456', '董事局');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `num` int(5) DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1005 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1001, '董事局', 2, '1234654');
INSERT INTO `department` VALUES (1002, '技术部', 3, '4564879');
INSERT INTO `department` VALUES (1003, '财务部', 0, '45679811');
INSERT INTO `department` VALUES (1004, '人事部', 2, '12348785');

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `status` int(2) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES (11, '10016', '2019-05-29', '这是一条短的测试内容', '这是一条测试标题', 0);
INSERT INTO `msg` VALUES (12, '10014', '2019-05-29', '这是一条短的测试内容', '这是一条测试标题', 0);

-- ----------------------------
-- Table structure for registry
-- ----------------------------
DROP TABLE IF EXISTS `registry`;
CREATE TABLE `registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of registry
-- ----------------------------
INSERT INTO `registry` VALUES (2, '10016');
INSERT INTO `registry` VALUES (6, '10014');

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '职称',
  `basic_salary` float(10, 2) DEFAULT NULL,
  `bonus` float(10, 2) DEFAULT NULL COMMENT '奖金',
  `basic_years_salary` float(10, 2) DEFAULT NULL COMMENT '工龄工资',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES (18, '董事局', '总经理', 8000.00, 2000.00, 0.00);
INSERT INTO `rules` VALUES (19, '董事局', '助理', 2000.00, 800.00, 0.00);
INSERT INTO `rules` VALUES (20, '技术部', '经理', 5000.00, 1000.00, 0.00);
INSERT INTO `rules` VALUES (21, '技术部', '工程师', 4000.00, 800.00, 0.00);
INSERT INTO `rules` VALUES (22, '技术部', '助理', 2000.00, 500.00, 0.00);
INSERT INTO `rules` VALUES (23, '技术部', '实习生', 3000.00, 500.00, 0.00);
INSERT INTO `rules` VALUES (25, '财务部', '财务经理', 6000.00, 2000.00, 0.00);
INSERT INTO `rules` VALUES (26, '财务部', '工程师', 5000.00, 1000.00, 0.00);
INSERT INTO `rules` VALUES (27, '人事部', '助理', 2000.00, 0.00, 0.00);
INSERT INTO `rules` VALUES (28, '人事部', '职工', 2000.00, 0.00, 0.00);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `psd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '123456',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `service_time` int(4) DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `in_the_time` datetime(0) DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `titles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('10014', '安耀锋', '456789', '男', NULL, 3, '15258111123', '技术部', '工程师', '温州商学院F1-703', NULL, ' ', ' ', '', 'ayf@163.com', '天真开朗-乐观');
INSERT INTO `users` VALUES ('10015', '蒋佩凯', '123456', '男', NULL, 1, '15256542323', '董事局', '总经理', '温州商学院', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('10016', '张三', '123456', '男', NULL, 1, '13625757478', '人事部', '职工', '滨海路12号', NULL, ' ', ' ', NULL, NULL, NULL);
INSERT INTO `users` VALUES ('10021', '谢逊', '123456', '女', NULL, 2, '15988228313', '人事部', '助理', '滨海路17号', NULL, '休假（带薪）', ' ', NULL, NULL, NULL);
INSERT INTO `users` VALUES ('10024', '周芷若', '123456', '女', NULL, 2, '15988228345', '董事局', '总经理', '滨海路20号', NULL, ' ', ' ', NULL, NULL, NULL);
INSERT INTO `users` VALUES ('10026', '赵敏', '123456', '女', NULL, 2, '13688225513', '财务部', '工程师', '滨海路22号', NULL, '休假（带薪）', ' ', NULL, NULL, NULL);
INSERT INTO `users` VALUES ('10027', '琦玉', '123456', '男', NULL, 1, '15957759696', '技术部', '实习生', '温州商学院F1-703', NULL, ' ', ' ', 'null', 'null', 'null');

-- ----------------------------
-- Table structure for wage
-- ----------------------------
DROP TABLE IF EXISTS `wage`;
CREATE TABLE `wage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '工号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `basic_salary` float(10, 2) DEFAULT NULL COMMENT '基本工资与职位 对应',
  `bonus` float(10, 2) DEFAULT NULL COMMENT '奖金',
  `basic_years_salary` float(10, 2) DEFAULT NULL COMMENT '工龄工资 工龄*工作时间',
  `others` float(10, 2) DEFAULT NULL COMMENT '其他',
  `count` float(10, 0) DEFAULT NULL COMMENT '总计',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
