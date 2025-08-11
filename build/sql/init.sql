DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `is_deleted` boolean NOT NULL DEFAULT FALSE COMMENT '删除标记',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '活动内容',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '活动状态 1-待办 2-完成',
  `estimate_pomodoro_cnt_1st` int DEFAULT NULL COMMENT '第一次预计专注数',
  `deviation_reason_1st` varchar(255) DEFAULT NULL COMMENT '第一次预计专注数偏离原因',
  `estimate_pomodoro_cnt_2nd` int DEFAULT NULL COMMENT '第二次预计专注数',
  `deviation_reason_2nd` varchar(255) DEFAULT NULL COMMENT '第二次预计专注数偏离原因',
  `estimate_pomodoro_cnt_3rd` int DEFAULT NULL COMMENT '第三次预计专注数',
  `deviation_reason_3rd` varchar(255) DEFAULT NULL COMMENT '第三次预计专注数偏离原因',
  UNIQUE KEY(`title`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '计划ID',
  `is_deleted` boolean NOT NULL DEFAULT FALSE COMMENT '删除标记',
  `plan_date` date NOT NULL COMMENT '计划日期',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '计划状态 1-制定中 2-制定完成',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`plan_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `task_plan_map`;
CREATE TABLE `task_plan_map` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '计划活动映射表ID',
  `is_deleted` boolean NOT NULL DEFAULT FALSE COMMENT '删除标记',
  `task_id` int NOT NULL COMMENT '所计划的活动',
  `plan_id` int NOT NULL COMMENT '活动分配计划',
  `plan_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '计划类型 1-计划内活动 2-计划外紧急活动',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`plan_id`, `task_id`),
  KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `pomodoro`;
CREATE TABLE `pomodoro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_deleted` boolean NOT NULL DEFAULT FALSE COMMENT '删除标记',
  `task_id` int DEFAULT NULL COMMENT '所专注的活动',
  `plan_id` int NOT NULL COMMENT '活动分配计划',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '专注状态 1-专注 2-休息 3-完成',
  `focus_duration` varchar(50) DEFAULT NULL COMMENT '专注预计时长',
  `focus_start_time` datetime(6) DEFAULT NULL COMMENT '专注实际开始时间',
  `focus_end_time` datetime(6) DEFAULT NULL COMMENT '专注实际结束时间',
  `focus_aborted` boolean NOT NULL DEFAULT FALSE COMMENT '专注是否被废止',
  `break_duration` varchar(50) DEFAULT NULL COMMENT '休息预计时长',
  `break_start_time` datetime(6) DEFAULT NULL COMMENT '休息实际开始时间',
  `break_end_time` datetime(6) DEFAULT NULL COMMENT '休息实际结束时间',
  `break_aborted` boolean NOT NULL DEFAULT FALSE COMMENT '休息是否被跳过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `interruption`;
CREATE TABLE `interruption` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_deleted` boolean NOT NULL DEFAULT FALSE COMMENT '删除标记',
  `pomodoro_id` int DEFAULT NULL COMMENT '所中断的专注',
  `int_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '中断类型 1-内部中断 2-外部中断',
  `start_time` datetime(6) DEFAULT NULL COMMENT '中断开始时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
