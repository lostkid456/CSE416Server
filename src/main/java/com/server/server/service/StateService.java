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
        Optional<State> queryState= stateRepository.findById(id);
        if(queryState.isPresent()){
            queryState.get().setStateEnsemble(this.getEnsemble(id).get());
        }
        return queryState;
    }

    public State addState(State state){
        return stateRepository.save(state);
    }

    public Optional<Ensemble> getEnsemble(String id){
        Optional<Ensemble> ensemble=ensembleRepository.findById(id);
        if(ensemble.isPresent()){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                switch (id) {
                    case "OH" -> {
                        File ohioMap = ResourceUtils.getFile("classpath:geoJson/oh_cong_adopted_2022.json");
                        Map<String, Object> ohioData = objectMapper.readValue(ohioMap, new TypeReference<>() {
                        });
                        ensemble.get().setCurrentDistrictPlan(ohioData);
                    }
                    case "NC" -> {
                        File ncMap = ResourceUtils.getFile("classpath:geoJson/NC_SMmap2_Statewide.json");
                        Map<String, Object> ncData = objectMapper.readValue(ncMap, new TypeReference<>() {
                        });
                        ensemble.get().setCurrentDistrictPlan(ncData);
                    }
                    case "FL" -> {
                        File flordiaMap = ResourceUtils.getFile("classpath:geoJson/P000C0109.json");
                        Map<String, Object> flordiaData = objectMapper.readValue(flordiaMap, new TypeReference<>() {
                        });
                        ensemble.get().setCurrentDistrictPlan(flordiaData);
                    }
                    default -> {
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return ensemble;
    }

    public Ensemble addEnsemble(String state) {
        return ensembleRepository.save(new Ensemble(state));
    }


}
