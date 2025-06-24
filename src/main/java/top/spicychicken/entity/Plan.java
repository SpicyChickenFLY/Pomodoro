package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "plan")
@Data
@IdClass(Plan.PlanId.class)
public class Plan {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Id
    @Column(name = "plan_date")
    private LocalDate planDate;

    // 复合主键类
    @Data
    public static class PlanId implements Serializable {
        private Integer task;
        private LocalDate planDate;
    }
}
