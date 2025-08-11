package top.spicychicken.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import io.swagger.annotations.ApiModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "task")
@Data
public class Task {
    public static Integer STATUS_TODO = 1;
    public static Integer STATUS_DONE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private Integer status = STATUS_TODO;

    @Column(name = "estimate_pomodoro_cnt_1st")
    private Integer estimatePomodoroCnt1st;

    @Column(name = "deviation_reason_1st")
    private String deviationReason1st;

    @Column(name = "estimate_pomodoro_cnt_2nd")
    private Integer estimatePomodoroCnt2nd;

    @Column(name = "deviation_reason_2nd")
    private String deviationReason2nd;

    @Column(name = "estimate_pomodoro_cnt_3rd")
    private Integer estimatePomodoroCnt3rd;

    @Column(name = "deviation_reason_3rd")
    private String deviationReason3rd;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pomodoro> pomodoros = new ArrayList<>();

    @JsonBackReference("task-ref") // 反向忽略序列化
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskPlanMap> taskPlanMaps = new ArrayList<>();
}
