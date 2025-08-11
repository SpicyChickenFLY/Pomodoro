package top.spicychicken.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import top.spicychicken.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findByPlanDate(LocalDate date);
    List<Plan> findByPlanDateBetween(LocalDate start, LocalDate end);
    List<Plan> findByStatus(Integer status);

    @Query(value = "SELECT * FROM plan WHERE status = 0", nativeQuery = true)
    List<Plan> findDeletedPlans();
}
