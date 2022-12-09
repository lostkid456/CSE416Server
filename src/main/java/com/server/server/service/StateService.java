package com.server.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.DistrictPlan;
import com.server.server.model.Ensemble;
import com.server.server.model.State;
import com.server.server.repository.DistrictPlanRepository;
import com.server.server.repository.EnsembleRepository;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public Map<String,Object> getHomeMap(){
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            File homeMap= ResourceUtils.getFile("classpath:geoJson/tl_2022_us_state.json");
            return objectMapper.readValue(homeMap, new TypeReference<>() {});
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    public State getState(String state){
        State currState=stateRepository.findByState(state);
        return currState;
    }

    public Map<String,Object> getStateMap(String state){
        State currState=stateRepository.findByState(state);
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            File stateMap=
                    ResourceUtils.getFile("classpath:geoJson/"+currState.getEnsembles().get(0).getDistrictPlans().
                            get(0).getDistrictBoundaryPath());
            return objectMapper.readValue(stateMap, new TypeReference<>() {
            });
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public State addState(State state){
        return stateRepository.save(state);
    }
}
