package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pomodoro")
@Data
public class Pomodoro {
    public static Integer STATUS_FOCUS = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private Plan plan;

    @Column(name = "status")
    private Integer status = STATUS_FOCUS;

    @Column(name = "focus_duration", columnDefinition = "VARCHAR(50)") // 自动存储为"PT8H6M12.345S"格式
    private Duration focusDuration;

    @Column(name = "focus_start_time")
    private LocalDateTime focusStartTime;

    @Column(name = "focus_end_time")
    private LocalDateTime focusEndTime;

    @Column(name = "focus_aborted")
    private boolean focusAborted;

    @Column(name = "break_duration", columnDefinition = "VARCHAR(50)") // 自动存储为"PT8H6M12.345S"格式
    private Duration breakDuration;

    @Column(name = "break_start_time")
    private LocalDateTime breakStartTime;

    @Column(name = "break_end_time")
    private LocalDateTime breakEndTime;

    @Column(name = "break_aborted")
    private Boolean breakAborted;

    @OneToMany(mappedBy = "pomodoro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interruption> interruptions = new ArrayList<>();
}
