package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "task")
@SQLRestriction("status <> 0")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "status")
    private Integer status;

    @Column(name = "estimate_pomodoro_cnt_1st")
    private Integer estimatePomodoroCnt1st;

    @Column(name = "deviation_reason_1st", length = 255)
    private String deviationReason1st;

    @Column(name = "estimate_pomodoro_cnt_2nd")
    private Integer estimatePomodoroCnt2nd;

    @Column(name = "deviation_reason_2nd", length = 255)
    private String deviationReason2nd;

    @Column(name = "estimate_pomodoro_cnt_3rd")
    private Integer estimatePomodoroCnt3rd;

    @Column(name = "deviation_reason_3rd", length =255)
    private String deviationReason3rd;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pomodoro> pomodoros = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans = new ArrayList<>();
}
