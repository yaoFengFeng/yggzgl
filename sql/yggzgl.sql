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

 Date: 25/05/2019 10:07:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

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
INSERT INTO `department` VALUES (1001, '董事局', 12, '1234654');
INSERT INTO `department` VALUES (1002, '技术部', 15, '4564879');
INSERT INTO `department` VALUES (1003, '财务部', 8, '45679811');
INSERT INTO `department` VALUES (1004, '人事部', 19, '12348785');

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
  PRIMARY KEY (`id`, `department`, `title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES (6, '技术部', '经理', 5000.00, 1000.00, 800.00);
INSERT INTO `rules` VALUES (7, '技术部', '工程师', 4000.00, 800.00, 600.00);
INSERT INTO `rules` VALUES (8, '技术部', '助理', 2000.00, 500.00, 0.00);
INSERT INTO `rules` VALUES (9, '董事局', '总经理', 8000.00, 2000.00, 0.00);
INSERT INTO `rules` VALUES (10, '人事部', '工程师', 6000.00, 500.00, 0.00);
INSERT INTO `rules` VALUES (11, '人事部', '职工', 2000.00, 500.00, 0.00);
INSERT INTO `rules` VALUES (12, '人事部', '助理', 2000.00, 500.00, 0.00);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('10014', '安耀锋', '123456', '男', NULL, 3, '152581110000', '技术部', '工程师', '温州商学院F1-703', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10016', '张三', '123456', '男', NULL, 1, '78945646664', '人事部', '职工', '滨海路12号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10020', '小邓', '123456', '男', NULL, 5, '15258671111', '技术部', '助理', '滨海路16号', NULL, '休假（带薪）', ' ');
INSERT INTO `users` VALUES ('10021', '谢逊', '123456', '女', NULL, 2, '27752572725', '人事部', '助理', '滨海路17号', NULL, '休假（带薪）', ' ');
INSERT INTO `users` VALUES ('10024', '周芷若', '123456', '女', NULL, 2, '12458787877', '董事局', '总经理', '滨海路20号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10025', '张无忌', '123456', '男', NULL, 2, '15258671111', '技术部', '经理', '滨海路21号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10026', '赵敏', '123456', '女', NULL, 2, '15258671111', '人事部', '工程师', '滨海路22号', NULL, '休假（带薪）', ' ');

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
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wage
-- ----------------------------
INSERT INTO `wage` VALUES (149, '2019-05-25', '技术部', '10014', '安耀锋', '工程师', 4000.00, 800.00, 1800.00, NULL, 6600, NULL);
INSERT INTO `wage` VALUES (150, '2019-05-25', '人事部', '10016', '张三', '职工', 2000.00, 500.00, 0.00, NULL, 2500, NULL);
INSERT INTO `wage` VALUES (151, '2019-05-25', '技术部', '10020', '小邓', '助理', 2000.00, 500.00, 0.00, NULL, 2500, NULL);
INSERT INTO `wage` VALUES (152, '2019-05-25', '人事部', '10021', '谢逊', '助理', 2000.00, 500.00, 0.00, NULL, 2500, NULL);
INSERT INTO `wage` VALUES (153, '2019-05-25', '董事局', '10024', '周芷若', '总经理', 8000.00, 2000.00, 0.00, NULL, 10000, NULL);
INSERT INTO `wage` VALUES (154, '2019-05-25', '技术部', '10025', '张无忌', '经理', 5000.00, 1000.00, 1600.00, NULL, 7600, NULL);
INSERT INTO `wage` VALUES (155, '2019-05-25', '人事部', '10026', '赵敏', '工程师', 6000.00, 500.00, 0.00, NULL, 6500, NULL);
INSERT INTO `wage` VALUES (156, '2019-05-25', '技术部', '10014', '安耀锋', '工程师', 4000.00, 800.00, 1800.00, NULL, 6600, NULL);
INSERT INTO `wage` VALUES (157, '2019-05-25', '人事部', '10016', '张三', '职工', 2000.00, 500.00, 0.00, NULL, 2500, NULL);
INSERT INTO `wage` VALUES (158, '2019-05-25', '技术部', '10020', '小邓', '助理', 2000.00, 500.00, 0.00, NULL, 2500, NULL);
INSERT INTO `wage` VALUES (159, '2019-05-25', '人事部', '10021', '谢逊', '助理', 2000.00, 500.00, 0.00, NULL, 2500, NULL);
INSERT INTO `wage` VALUES (160, '2019-05-25', '董事局', '10024', '周芷若', '总经理', 8000.00, 2000.00, 0.00, NULL, 10000, NULL);
INSERT INTO `wage` VALUES (161, '2019-05-25', '技术部', '10025', '张无忌', '经理', 5000.00, 1000.00, 1600.00, NULL, 7600, NULL);
INSERT INTO `wage` VALUES (162, '2019-05-25', '人事部', '10026', '赵敏', '工程师', 6000.00, 500.00, 0.00, NULL, 6500, NULL);

SET FOREIGN_KEY_CHECKS = 1;
