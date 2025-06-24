package top.spicychicken.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Plan.PlanId;

public interface PlanRepository extends JpaRepository<Plan, PlanId> {
    List<Plan> findByPlanDate(LocalDate date);
    List<Plan> findByPlanDateBetween(LocalDate start, LocalDate end);
}
