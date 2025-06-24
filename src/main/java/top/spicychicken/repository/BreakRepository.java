package top.spicychicken.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import top.spicychicken.entity.Break;

public interface BreakRepository extends JpaRepository<Break, Integer> {

    /**
     * 根据番茄钟ID查询休息记录
     * 
     * @param pomodoroId 番茄钟ID
     * @return 休息记录列表
     */
    List<Break> findByPomodoroId(Integer pomodoroId);

    /**
     * 查询指定时间段内的休息记录
     * 
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 休息记录列表
     */
    List<Break> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 查询未结束的休息记录（duration为null）
     * 
     * @return 未结束的休息记录列表
     */
    @Query("SELECT b FROM Break b WHERE b.duration IS NULL")
    List<Break> findUnfinishedBreaks();

    /**
     * 统计某个番茄钟的总休息时间
     * 
     * @param pomodoroId 番茄钟ID
     * @return 总休息时间(秒)
     */
    @Query("SELECT COALESCE(SUM(TIME_TO_SEC(b.duration)), 0) FROM Break b WHERE b.pomodoro.id = :pomodoroId")
    Long sumDurationByPomodoroId(@Param("pomodoroId") Integer pomodoroId);
}
