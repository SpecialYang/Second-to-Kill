CREATE TABLE `seconds_to_kill_user` (
  `id` BIGINT(20) NOT NULL COMMENT "用户id",
  `nickname` varchar(20) NOT NULL,
  `password` varchar(32) DEFAULT NULL COMMENT "MD5(MD5(pass明文+固定salt)+salt"
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL COMMENT "头像，云存储的id",
  `register_date` datetime DEFAULT NULL COMMENT "注册时间",
  `last_login_date` datetime DEFAULT NULL COMMENT "上次登录时间",
  `login_count` int(11) DEFAULT 0 COMMENT "登录次数",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';