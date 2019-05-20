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

 Date: 20/05/2019 07:59:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
INSERT INTO `users` VALUES ('10014', '安耀锋', '123456', '男', NULL, 2, '15258671111', '技术部', '工程师', '紫禁城乾清宫', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10015', '小王', '123456', '女', NULL, 1, '15258671111', '技术部', '职工', '滨海路11号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10016', '张三', '123456', '男', NULL, 1, '78945646664', '人事部', '职工', '滨海路12号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10017', '李四', '123456', '男', NULL, 5, '15258671111', '销售部', '工程师', '滨海路13号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10018', '王五', '123456', '男', NULL, 2, '15258671111', '销售部', '助理', '滨海路14号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10019', '小明', '123456', '女', NULL, 2, '1215478787', '董事局', '工程师', '滨海路15号', NULL, '休假（带薪）', ' ');
INSERT INTO `users` VALUES ('10020', '小邓', '123456', '男', NULL, 5, '15258671111', '技术部', '助理', '滨海路16号', NULL, '休假（带薪）', ' ');
INSERT INTO `users` VALUES ('10021', '谢逊', '123456', '女', NULL, 2, '27752572725', '人事部', '助理', '滨海路17号', NULL, '休假（带薪）', ' ');
INSERT INTO `users` VALUES ('10022', '张翠山', '123456', '男', NULL, 1, '15258671111', '销售部', '职工', '滨海路18号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10023', '玄冥二老', '123456', '男', NULL, 8, '15258671111', '技术部', '工程师', '滨海路19号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10024', '周芷若', '123456', '女', NULL, 2, '12458787877', '董事局', '总经理', '滨海路20号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10025', '张无忌', '123456', '男', NULL, 2, '15258671111', '技术部', '经理', '滨海路21号', NULL, ' ', ' ');
INSERT INTO `users` VALUES ('10026', '赵敏', '123456', '女', NULL, 2, '15258671111', '人事部', '工程师', '滨海路22号', NULL, '休假（带薪）', ' ');

SET FOREIGN_KEY_CHECKS = 1;
