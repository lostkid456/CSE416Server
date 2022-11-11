package com.server.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.State;
import com.server.server.repository.EnsembleRepository;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Map;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private EnsembleRepository ensembleRepository;

    public State getState(String state){
        State foundState=stateRepository.findByState(state);
        System.out.println("5 " +foundState);
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String,Object> currentJson=objectMapper.readValue(ResourceUtils.getFile(
                    "classpath:geoJson/"+foundState.getEnsemble().getPlanGeoJsonPath()), new TypeReference<>() {
                    });
            System.out.println("6: "+foundState.getEnsemble());
            foundState.getEnsemble().setCurrentDistrictPlan(currentJson);
            return foundState;
        } catch (IOException e) {
            System.out.println("OH NO");
        }
        return null;
    }

    public State addState(State state){
        return stateRepository.save(state);
    }
}
