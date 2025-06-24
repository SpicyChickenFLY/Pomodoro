package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "break")
@Data
public class Break {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pomodoro_id", referencedColumnName = "id")
    private Pomodoro pomodoro;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "duration")
    private Duration duration;
}
