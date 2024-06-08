SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database if not exists bh_im;
use bh_im;
-- ----------------------------
-- Table structure for im_friend
-- ----------------------------
DROP TABLE IF EXISTS `im_friend`;
CREATE TABLE `im_friend`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `friend_id` bigint(20) NOT NULL COMMENT '好友id',
  `friend_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '好友昵称',
  `friend_head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '好友头像',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_friend_id`(`friend_id`) USING BTREE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '好友';

-- ----------------------------
-- Table structure for im_group
-- ----------------------------
DROP TABLE IF EXISTS `im_group`;
CREATE TABLE `im_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '群名字',
  `owner_id` bigint(20) NOT NULL COMMENT '群主id',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '群头像',
  `head_image_thumb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '群头像缩略图',
  `notice` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '群公告',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '群备注',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群';

-- ----------------------------
-- Table structure for im_group_member
-- ----------------------------
DROP TABLE IF EXISTS `im_group_member`;
CREATE TABLE `im_group_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) NOT NULL COMMENT '群id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `alias_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '组内显示名称',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `quit` tinyint(1) NULL DEFAULT 0 COMMENT '是否已退出',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群成员';

-- ----------------------------
-- Table structure for im_group_message
-- ----------------------------
DROP TABLE IF EXISTS `im_group_message`;
CREATE TABLE `im_group_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) NOT NULL COMMENT '群id',
  `send_id` bigint(20) NOT NULL COMMENT '发送用户id',
  `send_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '发送用户昵称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '发送内容',
  `at_user_ids` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '被@的用户id列表，逗号分隔',
  `type` tinyint(1) NOT NULL COMMENT '消息类型 0:文字 1:图片 2:文件 3:语音 10:系统提示',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 0:正常  2:撤回',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群消息';

-- ----------------------------
-- Table structure for im_private_message
-- ----------------------------
DROP TABLE IF EXISTS `im_private_message`;
CREATE TABLE `im_private_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `send_id` bigint(20) NOT NULL COMMENT '发送用户id',
  `recv_id` bigint(20) NOT NULL COMMENT '接收用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '发送内容',
  `type` tinyint(1) NOT NULL COMMENT '消息类型 0:文字 1:图片 2:文件 3:语音 10:系统提示',
  `status` tinyint(1) NOT NULL COMMENT '状态 0:未读 1:已读 2:撤回',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_send_recv_id`(`send_id`, `recv_id`) USING BTREE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '私聊消息';

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
  `head_image_thumb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像缩略图',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别 0:男 1:女',
  `type` smallint(6) NULL DEFAULT 1 COMMENT '用户类型 1:普通用户 2:审核账户',
  `signature` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '个性签名',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_name`(`user_name`) USING BTREE,
  INDEX `idx_nick_name`(`nick_name`) USING BTREE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户';

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `bh_im`.`im_user`(`id`, `user_name`, `nick_name`, `head_image`, `head_image_thumb`, `password`, `sex`, `type`, `signature`, `last_login_time`, `created_time`) VALUES (10000000001, 'binghe', 'binghe', 'http://127.0.0.1:9000/bh-im/image/20240218/1708269405038.jpg', 'http://127.0.0.1:9000/bh-im/image/20240218/1708269405144.jpg', '$2a$10$zNg07bH1WLTsInbI6iRmhexsk.CcXgHTV2c87UAVRnFDukAAZ9jMu', 0, 1, '', NULL, '2024-02-12 21:13:55');

