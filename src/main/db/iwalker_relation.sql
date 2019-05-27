CREATE TABLE `iwalker_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `applicant` varchar(20) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '0未处理 1同意 2拒绝 ',
  `extra` text DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;