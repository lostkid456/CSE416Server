package com.server.server.service;

import com.server.server.model.StateDemographic;
import com.server.server.repository.StateDemographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateDemographicService {
    @Autowired
    private StateDemographicRepository stateDemographicRepository;


}
