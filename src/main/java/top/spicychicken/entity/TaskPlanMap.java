package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "task_plan_map")
@Data
@IdClass(TaskPlanMap.TaskPlanMapId.class)
public class TaskPlanMap {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private Plan plan;

    @Column(name = "plan_type")
    private Integer planType;

    // 复合主键类
    @Data
    public static class TaskPlanMapId implements Serializable {
        private Integer task;
        private Integer plan;
    }
}

