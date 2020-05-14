SET NAMES utf8mb4;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `authority` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phoneNo`(`phone_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


INSERT INTO `user`(`id`, `name`, `birthday`, `authority`, `password`, `sex`, `phone_no`) VALUES ('000000000000000000000000', 'BOSS', '2019-08-13', 'boss', '$10$Wbt9yHn8TSuLgo.uDawMYeEeUjh4f.2DjkuxnwWvPra/6V1RoPx2G', '1', '18888888888');
