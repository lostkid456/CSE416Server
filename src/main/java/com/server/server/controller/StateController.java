package com.server.server.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.server.server.model.*;
import com.server.server.model.enums.InterestTypes;
import com.server.server.model.relationships.BWType;
import com.server.server.model.relationships.IsMMD;
import com.server.server.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    private RepDemSplitService repDemSplitService;


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

    @GetMapping("/home/map/{state}")
    public State getStateMap(@PathVariable String state){
        State stateHome=stateService.getState(state);
        stateHome.getEnsemble().setRepDemSplits(null);
        stateHome.setStateDemographic(null);
        stateHome.getEnsemble().setDistrictPlans(null);
        stateHome.getEnsemble().setBoxAndWhiskers(null);
        return stateHome;
    }

    @GetMapping("/home/{state}")
    public State getStateHome(@PathVariable String state){
        State stateHome=stateService.getStateNoMap(state);
        stateHome.getEnsemble().setDistrictPlans(null);
        stateHome.getEnsemble().setBoxAndWhiskers(null);
        stateHome.getEnsemble().setRepDemSplits(null);
        return stateHome;
    }

    @GetMapping("/districtplan/boxwhisker/{state}")
    public State getBoxWhisker(@PathVariable String state){
        State stateInfo=stateService.getStateNoMap(state);
        stateInfo.getEnsemble().setDistrictPlans(null);
        stateInfo.setStateDemographic(null);
        stateInfo.getEnsemble().setRepDemSplits(null);
        return stateInfo;
    }

    @GetMapping("/districtplan/bargraph/{state}")
    public State getBarGraph(@PathVariable String state){
        State stateInfo=stateService.getStateNoMap(state);
        stateInfo.getEnsemble().setBoxAndWhiskers(null);
        stateInfo.setStateDemographic(null);
        return stateInfo;
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
                break;
            case "FL":
                ensemble.setPlanGeoJsonPath("P000C0109.json");
                break;
            case "NC":
                ensemble.setPlanGeoJsonPath("NC_SMmap2_Statewide.json");
                break;
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
        JsonNode array=objectNode.get("splits");
        Iterator<JsonNode> itr=array.iterator();

        List<RepDemSplit> newSplits=new ArrayList<>();
        while(itr.hasNext()){
            JsonNode item=itr.next();
            RepDemSplit newSplit=new RepDemSplit();
            newSplit.setNumberOfPlans(item.get("plans").asInt());
            newSplit.setSplit(item.get("split").asText());
            newSplits.add(newSplit);
        }
        ensemble.setRepDemSplits(newSplits);
        state.setEnsemble(ensemble);
        return stateService.addState(state);
    }
}
