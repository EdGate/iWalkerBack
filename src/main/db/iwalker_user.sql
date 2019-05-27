CREATE TABLE `iwalker_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar (15) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT '1' COMMENT '1男性 0女性',
  `status` tinyint(4) DEFAULT '1' COMMENT '1正常 0注销',
  `extra` text DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name_id` (`user_name`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
