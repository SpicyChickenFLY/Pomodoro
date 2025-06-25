DROP TABLE IF EXISTS `interruption`;
CREATE TABLE `interruption` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pomodoro_id` int DEFAULT NULL,
  `int_type` tinyint(1) DEFAULT NULL COMMENT '中断类型 1-内部中断 2-外部中断',
  `start_time` datetime(6) DEFAULT NULL COMMENT '中断开始时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `task_id` int NOT NULL COMMENT '所计划的活动',
  `plan_date` date NOT NULL COMMENT '计划专注日期',
  PRIMARY KEY (`task_id`,`plan_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `pomodoro`;
CREATE TABLE `pomodoro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` int DEFAULT NULL COMMENT '所专注的活动',
  `focus_start_time` datetime(6) DEFAULT NULL COMMENT '专注开始时间',
  `focus_duration` time DEFAULT NULL COMMENT '专注时长',
  `break_start_time` datetime(6) DEFAULT NULL COMMENT '休息开始时间',
  `break_duration` time DEFAULT NULL COMMENT '休息时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '活动内容',
  `status` tinyint(1) DEFAULT NULL COMMENT '活动类型',
  `estimate_pomodoro_cnt` int DEFAULT NULL COMMENT '预计专注数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
