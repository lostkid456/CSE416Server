package com.server.server.repository;

import com.server.server.model.DistrictPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictPlanRepository extends JpaRepository<DistrictPlan,Integer> {
}
