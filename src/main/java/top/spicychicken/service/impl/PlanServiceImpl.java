package top.spicychicken.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Plan;
import top.spicychicken.repository.PlanRepository;
import top.spicychicken.service.PlanService;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    public Plan getPlanById(Integer id) {
        return planRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Plan> getPlansByDateRange(LocalDate startDate, LocalDate endDate) {
        return planRepository.findByPlanDateBetween(startDate, endDate);
    }

    public Plan createPlan(Plan plan) {
        plan.setId(null);
        return planRepository.save(plan);
    }

    public Plan updatePlan(Integer id, Plan delta) {
        Plan plan = planRepository.findById(id).orElseThrow(RuntimeException::new);
        if (delta.getPlanDate() != null) {
            plan.setPlanDate(delta.getPlanDate());
        }
        if (delta.getStatus() != null) {
            plan.setStatus(delta.getStatus());
        }
        return planRepository.save(plan);
    }
}
