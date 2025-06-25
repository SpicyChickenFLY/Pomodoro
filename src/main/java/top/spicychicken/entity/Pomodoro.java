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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Column(name = "focus_start_time")
    private LocalDateTime focusStartTime;

    @Column(name = "focus_duration")
    private Duration focusDuration;

    @Column(name = "break_start_time")
    private LocalDateTime breakStartTime;

    @Column(name = "break_duration")
    private Duration breakDuration;

    @OneToMany(mappedBy = "pomodoro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interruption> interruptions = new ArrayList<>();
}
