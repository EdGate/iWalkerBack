CREATE TABLE `iwalker_activity` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `content` varchar(200) NOT NULL,
  `location` varchar(50) NOT NULL COMMENT '地理位置经纬度字符串',
  `location_name` varchar(50) NOT NULL,
  `like_num` int(11) DEFAULT '0',
  `extra` text DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `iwalker_image` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) unsigned NOT NULL,
  `image` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `order` int(4) unsigned NOT NULL COMMENT '图片序号',
  `extra` text DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* 点赞 */
CREATE TABLE `iwalker_like` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `activity_id` int(11) unsigned NOT NULL,
  `extra` text DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* 插入动态数据 */
INSERT INTO `iwalker_activity` VALUES (1, 'zhang', '这是修改之后的内容', '134.531,12345.467', '{\"contury\": \"中国\", \"xx\": \"xx\"}', 0, NULL, '2019-5-28 14:43:24', '2019-5-28 16:22:29');
INSERT INTO `iwalker_activity` VALUES (3, 'zhang', '这是动态的内容,需要登录', '134.531,12345.467', '{\"contury\": \"中国\", \"xx\": \"xx\"}', 0, NULL, '2019-5-28 16:24:01', '2019-5-28 16:24:01');

/* 插入动态图片 */
INSERT INTO `iwalker_image` VALUES (1, 1, 'http://localhost:9969/1559028184238timg (3).jpg', 1, NULL, '2019-5-28 15:23:04', '2019-5-28 15:23:04');
INSERT INTO `iwalker_image` VALUES (2, 1, 'http://localhost:9969/1559029276727captures_the_beauty_of_daily_life_200910032946-12383.jpg', 2, NULL, '2019-5-28 15:41:16', '2019-5-28 15:41:16');
INSERT INTO `iwalker_image` VALUES (4, 1, 'http://localhost:9969/155903054393203853475704d43f6ac725794887476a.jpg', 3, NULL, '2019-5-28 16:02:23', '2019-5-28 16:02:23');
