package top.spicychicken.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "plan")
@SQLRestriction("status <> 0")
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "plan_date")
    private LocalDate planDate;

    @Column(name = "status")
    private Integer status;

    @JsonBackReference("plan-ref") // 反向忽略序列化
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskPlanMap> taskPlanMaps = new ArrayList<>();
}
