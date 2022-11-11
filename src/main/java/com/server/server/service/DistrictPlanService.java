package com.server.server.service;

import com.server.server.model.DistrictPlan;
import com.server.server.repository.DistrictPlanRepository;
import com.server.server.repository.isMMDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictPlanService {
    @Autowired
    private DistrictPlanRepository districtPlanRepository;
    @Autowired
    private isMMDRepository isMMDRepository;

    public DistrictPlan addDistrictPlan(DistrictPlan districtPlan){
        return districtPlan;
    }
}
