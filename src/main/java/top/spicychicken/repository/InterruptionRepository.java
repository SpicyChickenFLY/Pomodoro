package top.spicychicken.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import top.spicychicken.entity.Interruption;

public interface InterruptionRepository extends JpaRepository<Interruption, Integer> {

    /**
     * 根据番茄钟ID查询中断记录
     * 
     * @param pomodoroId 番茄钟ID
     * @return 中断记录列表
     */
    List<Interruption> findByPomodoroId(Integer pomodoroId);

    /**
     * 根据中断类型查询中断记录
     * 
     * @param type 中断类型(1-内部中断, 2-外部中断)
     * @return 中断记录列表
     */
    List<Interruption> findByInterruptionType(Integer type);

    // /**
    //  * 查询指定时间段内的中断记录
    //  * 
    //  * @param startTime 开始时间
    //  * @param endTime   结束时间
    //  * @return 中断记录列表
    //  */
    // List<Interruption> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    //
    // /**
    //  * 统计某个番茄钟的中断次数
    //  * 
    //  * @param pomodoroId 番茄钟ID
    //  * @return 中断次数
    //  */
    // @Query("SELECT COUNT(i) FROM Interruption i WHERE i.pomodoro.id = :pomodoroId")
    // Long countByPomodoroId(@Param("pomodoroId") Integer pomodoroId);
    //
    // /**
    //  * 按类型统计中断次数
    //  * 
    //  * @param pomodoroId 番茄钟ID
    //  * @param type       中断类型
    //  * @return 中断次数
    //  */
    // @Query("SELECT COUNT(i) FROM Interruption i WHERE i.pomodoro.id = :pomodoroId AND i.interruptionType = :type")
    // Long countByPomodoroIdAndType(@Param("pomodoroId") Integer pomodoroId,
    //         @Param("type") Integer type);
}
