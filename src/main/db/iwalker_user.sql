CREATE TABLE `iwalker_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar (15) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
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

/* 插入记录 */
INSERT INTO `iwalker_user` VALUES (3, 'zhang', '123', '09EEB592558D88F372F4AEC8DA4B4637', 'http://localhost:9969/1559014935829timg.jpg', NULL, 1, 1, NULL, '2019-5-28 10:31:01', '2019-5-28 11:42:15');
INSERT INTO `iwalker_user` VALUES (4, 'helloworld', 'zhang', '5B5061536787A1B741BB8F03E5543A75', 'http://localhost:9969/1559017852435timg (2).jpg', NULL, 1, 1, NULL, '2019-5-28 12:26:48', '2019-5-28 12:30:52');
