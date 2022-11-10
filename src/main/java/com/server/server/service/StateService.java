package com.server.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.Ensemble;
import com.server.server.model.State;
import com.server.server.repository.EnsembleRepository;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private EnsembleRepository ensembleRepository;

    public Optional<State> getState(String id){
        return stateRepository.findById(id);
    }

    public State addState(State state){
        return stateRepository.save(state);
    }

    public Optional<Ensemble> getEnsemble(String id){
        return ensembleRepository.findById(id);
    }

    public Ensemble addEnsemble(String state) {
        Ensemble newEnsemble = new Ensemble(state);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch (state) {
                case "OH" -> {
                    File ohioMap = ResourceUtils.getFile("classpath:geoJson/oh_cong_adopted_2022.json");
                    Map<String, Object> ohioData = objectMapper.readValue(ohioMap, new TypeReference<>() {
                    });
                    newEnsemble.setCurrentDistrictPlan(ohioData);
                }
                case "NC" -> {
                    File ncMap = ResourceUtils.getFile("classpath:geoJson/NC_SMmap2_Statewide.json");
                    Map<String, Object> ncData = objectMapper.readValue(ncMap, new TypeReference<>() {
                    });
                    newEnsemble.setCurrentDistrictPlan(ncData);
                }
                case "FL" -> {
                    File flordiaMap = ResourceUtils.getFile("classpath:geoJson/P000C0109.json");
                    Map<String, Object> flordiaData = objectMapper.readValue(flordiaMap, new TypeReference<>() {
                    });
                    newEnsemble.setCurrentDistrictPlan(flordiaData);
                }
                default -> {
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return newEnsemble;
    }


}
