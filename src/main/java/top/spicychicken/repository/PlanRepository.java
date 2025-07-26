package top.spicychicken.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.spicychicken.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findByPlanDate(LocalDate date);
    List<Plan> findByPlanDateBetween(LocalDate start, LocalDate end);
    List<Plan> findByStatus(Integer status);
}
