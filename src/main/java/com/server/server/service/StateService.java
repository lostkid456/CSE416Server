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
import java.util.List;
import java.util.Map;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private EnsembleRepository ensembleRepository;

    public Map<String,Object> getHomeMap(){
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            File homeMap= ResourceUtils.getFile("classpath:tl_2022_us_state.json");
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
        System.out.println();
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            File stateMap=
                    ResourceUtils.getFile("classpath:"+currState.getEnsembles().get(2).getDistrictPlans().
                            get(0).getDistrictBoundaryPath());
            return objectMapper.readValue(stateMap, new TypeReference<>() {
            });
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Map<String,Object> getMMDAverageMap(String state,String type){
        State currenState=stateRepository.findByState(state);
        List<Ensemble> ensembleList=currenState.getEnsembles();
        for(Ensemble ensemble:ensembleList){
            if(ensemble.getType().equals(type)){
                List<DistrictPlan> districtPlans=ensemble.getDistrictPlans();
                for(DistrictPlan districtPlan:districtPlans){
                    if(districtPlan.getPlanType().equals("MMD/average")){
                        try{
                            ObjectMapper objectMapper=new ObjectMapper();
                            File averageMap=ResourceUtils.getFile("classpath:"+districtPlan.getDistrictBoundaryPath());
                            return objectMapper.readValue(averageMap,new TypeReference<>(){});
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        return null;
    }

    public State addState(State state){
        return stateRepository.save(state);
    }
}
