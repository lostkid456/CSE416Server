package com.server.server.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.server.server.model.*;
import com.server.server.model.enums.InterestTypes;
import com.server.server.model.relationships.BWType;
import com.server.server.model.relationships.IsMMD;
import com.server.server.service.BoxAndWhiskerService;
import com.server.server.service.DistrictPlanService;
import com.server.server.service.EnsembleService;
import com.server.server.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private EnsembleService ensembleService;

    @Autowired
    private DistrictPlanService districtPlanService;

    @Autowired
    private BoxAndWhiskerService boxAndWhiskerService;

    @GetMapping("/home")
    public Map<String,Object> getHome(){
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            File home_map= ResourceUtils.getFile("classpath:geoJson/tl_2022_us_state.json");
            return objectMapper.readValue(home_map, new TypeReference<>() {});
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @GetMapping("/home/{state}")
    public State getState(@PathVariable String state){
        return stateService.getState(state);
    }

    @PostMapping("/addstate")
    public State addState(@RequestBody ObjectNode objectNode){
        State state=new State(objectNode.get("state").asText());
        StateDemographic stateDemographic=new StateDemographic();
        stateDemographic.setTotalPopulation(objectNode.get("totalPopulation").asInt());
        stateDemographic.setCaucasian(objectNode.get("caucasian").asInt());
        stateDemographic.setAfricanAmerican(objectNode.get("africanAmerican").asInt());
        stateDemographic.setAsian(objectNode.get("asian").asInt());
        stateDemographic.setLatino(objectNode.get("latino").asInt());
        state.setStateDemographic(stateDemographic);
        Ensemble ensemble=new Ensemble();
        switch(objectNode.get("state").asText()){
            case "OH":
                ensemble.setPlanGeoJsonPath("oh_cong_adopted_2022.json");
            case "FL":
                ensemble.setPlanGeoJsonPath("P000C0109.json");
            case "NC":
                ensemble.setPlanGeoJsonPath("NC_SMmap2_Statewide.json");
        }
        System.out.println(ensemble.getPlanGeoJsonPath());
        BoxAndWhisker boxAndWhisker=new BoxAndWhisker();
        BWType bwType=new BWType();
        bwType.setType(InterestTypes.valueOf(objectNode.get("interest").asText()));
        boxAndWhisker.setBwType(bwType);
        boxAndWhisker.setMin(objectNode.get("min").asInt());
        boxAndWhisker.setMax(objectNode.get("max").asInt());
        boxAndWhisker.setMedian(objectNode.get("median").asInt());
        boxAndWhisker.setFirstQ(objectNode.get("firstQ").asInt());
        boxAndWhisker.setThirdQ(objectNode.get("thirdQ").asInt());
        ensemble.setBoxAndWhiskers(List.of(boxAndWhisker));
        DistrictPlan districtPlan=new DistrictPlan();
        IsMMD isMMD=new IsMMD();
        isMMD.setIsMMD(objectNode.get("isMMD").asInt());
        districtPlan.setIsMMD(isMMD);
        districtPlan.setNumberOfDemocrats(objectNode.get("democrat").asInt());
        districtPlan.setNumberOfRepublicans(objectNode.get("republican").asInt());
        districtPlan.setNumberOfMajorityMinority(objectNode.get("majorityMinority").asInt());
        ensemble.setDistrictPlans(List.of(districtPlan));
        state.setEnsemble(ensemble);
        return stateService.addState(state);
    }
}
