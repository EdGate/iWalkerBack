CREATE TABLE `cpst_data_record_2019_04` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `obj_id` int(11) DEFAULT NULL COMMENT '关联实体的id',
  `item_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `pay_month` int(6) DEFAULT NULL,
  `approval_status` tinyint(4) DEFAULT NULL COMMENT '暂时没使用',
  `modify_status` tinyint(4) DEFAULT NULL COMMENT '数据的修改方式 10 数据接入  20 审批通过 30手动修改',
  `status` tinyint(4) DEFAULT '1',
  `extra` text DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_status` bigint(20) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_employee_item_month_datastatus` (`employee_id`,`item_id`,`pay_month`,`data_status`),
  KEY `idx_obj_id_eid` (`obj_id`,`employee_id`),
  KEY `idx_item_pay_month` (`item_id`,`pay_month`)
) ENGINE=InnoDB AUTO_INCREMENT=189083 DEFAULT CHARSET=utf8mb4 COMMENT='填报数据审核通过的数据';