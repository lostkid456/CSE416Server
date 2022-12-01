package com.server.server.service;

import com.server.server.model.DistrictPlan;
import com.server.server.repository.DistrictPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictPlanService {
    @Autowired
    private DistrictPlanRepository districtPlanRepository;


}
