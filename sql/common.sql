-- 列出所有活动
SELECT id, title, status, estimate_pomodoro_cnt  FROM task;
-- 查看活动和已专注数目
SELECT id, title, status, estimate_pomodoro_cnt, p.cnt
FROM task
LEFT JOIN (
    SELECT COUNT(*) as cnt, task_id
        FROM pomodoro
        GROUP BY task_id
) p
ON p.task_id = task.id;
-- 查看今日计划活动
SELECT t.id, t.title, t.status, t.estimate_pomodoro_cnt
    FROM plan p
LEFT JOIN task t ON p.task_id = t.id
WHERE p.plan_date = CURRENT_DATE;

-- 创建新活动
INSERT INTO task (
    title, status, estimate_pomodoro_cnt
) VALUES
    ( '番茄钟研发', 1, 1 );
-- 删除指定活动（需要改
DELETE FROM task
    WHERE id = 1;
-- 更新活动信息
UPDATE task
    SET title = '番茄工作法信息系统研发',
        status = 1,
        estimate_pomodoro_cnt = 8
    WHERE id = 1;

-- 列出所有专注
SELECT * FROM pomodoro;
-- 开始新专注
INSERT INTO pomodoro (
    task_id, start_time, duration
) VALUES ( 1, now(), '00:25:00' );
-- 查看今日专注
SELECT p.id, t.title, p.start_time, p.duration
    FROM pomodoro p
LEFT JOIN task t ON p.task_id = t.id
WHERE DATE(p.start_time) = CURRENT_DATE;

-- 查看所有活动计划
SELECT * FROM plan;
-- 添加新活动计划
INSERT INTO plan (
    task_id, plan_date
) VALUES
    ( 1, '2025-06-25' )

-- 查看所有中断
SELECT id, pomodoro_id, int_type, start_time FROM interruption;
-- 专注期间发生中断
INSERT INTO interruption (
    pomodoro_id, int_type, start_time
) VALUES
    ( 1, 1, now() );

-- 查看所有休息情况
-- 开始休息
