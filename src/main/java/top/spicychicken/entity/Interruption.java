package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "interruption")
@Data
public class Interruption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pomodoro_id", referencedColumnName = "id")
    private Pomodoro pomodoro;

    @Column(name = "int_type")
    private Integer interruptionType;

    @Column(name = "start_time")
    private LocalDateTime startTime;
}
