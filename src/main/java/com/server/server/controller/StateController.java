package com.server.server.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.server.server.model.*;
import com.server.server.model.enums.InterestType;
import com.server.server.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins ={"http://localhost:3000","http://127.0.0.1:5173/"} )
@RestController
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping("/home")
    public Map<String,Object> getHome(){
        return stateService.getHomeMap();
    }

    @GetMapping("/home/{state}")
    public State getState(@PathVariable String state){
        return stateService.getState(state);
    }

    @GetMapping("/map/{state}")
    public Map<String,Object> getStateMap(@PathVariable String state){
        return stateService.getStateMap(state);
    }

    @PostMapping("/addstate")
    public State addState(@RequestBody ObjectNode objectNode){
        List<District> districts=new ArrayList<>();
        JsonNode district1=objectNode.get("district1");
        for(JsonNode district:district1){
            District dist=new District();
            DistrictDemographic whiteDemographic=new DistrictDemographic();
            whiteDemographic.setType(InterestType.CAUCASIAN);
            whiteDemographic.setPopulation(district.get("CAUCASIAN").asInt());
            DistrictDemographic africanDemographic=new DistrictDemographic();
            africanDemographic.setType(InterestType.AFRICAN);
            africanDemographic.setPopulation(district.get("AFRICANAMERICAN").asInt());
            DistrictDemographic asianDemographic=new DistrictDemographic();
            asianDemographic.setType(InterestType.ASIAN);
            asianDemographic.setPopulation(district.get("ASIAN").asInt());
            DistrictDemographic latinoDemographic=new DistrictDemographic();
            latinoDemographic.setType(InterestType.LATINO);
            latinoDemographic.setPopulation(district.get("LATINO").asInt());
            List<DistrictDemographic> demographics=new ArrayList<>(Arrays.asList(whiteDemographic,africanDemographic,
                                                                                 asianDemographic,latinoDemographic));
            dist.setDistrictDemographic(demographics);
            districts.add(dist);
        }
        List<RepDemSplit> repDemSplits=new ArrayList<>();
        JsonNode rDS=objectNode.get("repD");
        for(JsonNode repDemSplit:rDS){
            RepDemSplit repDemS=new RepDemSplit();
            repDemS.setSplit(repDemSplit.get("split").asText());
            repDemS.setNumberOfPlan(repDemSplit.get("number").asInt());
            repDemSplits.add(repDemS);
        }
        List<DistrictPlan> districtPlans=new ArrayList<>();
        JsonNode districtPlanNode=objectNode.get("districtPlans");
        for(JsonNode districtPlan:districtPlanNode){
            DistrictPlan districtPlan1=new DistrictPlan();
            districtPlan1.setMMD(false);
            districtPlan1.setNumberOfDemocrat(districtPlan.get("democrat").asInt());
            districtPlan1.setNumberOfRepublican(districtPlan.get("republican").asInt());
            districtPlan1.setNumberOfMajorityMinority(districtPlan.get("majorityMinority").asInt());
            districtPlan1.setDistrictBoundaryPath(districtPlan.get("districtBoundaryPath").asText());
            districtPlans.add(districtPlan1);
        }
        districtPlans.get(0).setDistricts(districts);
        districtPlans.get(0).setRepDemSplits(repDemSplits);
        List<BoxAndWhisker> boxAndWhiskers=new ArrayList<>();
        JsonNode bw=objectNode.get("boxW");
        for(JsonNode boxW:bw){
            BoxAndWhisker boxAndWhisker=new BoxAndWhisker();
            boxAndWhisker.setType(InterestType.valueOf(boxW.get("type").asText()));
            boxAndWhisker.setMin(boxW.get("min").asDouble());
            boxAndWhisker.setMax(boxW.get("max").asDouble());
            boxAndWhisker.setFirstQ(boxW.get("firstQ").asDouble());
            boxAndWhisker.setMedian(boxW.get("median").asDouble());
            boxAndWhisker.setThirdQ(boxW.get("thirdQ").asDouble());
            boxAndWhiskers.add(boxAndWhisker);
        }
        List<Ensemble> ensembles=new ArrayList<>();
        JsonNode ensemble_arr=objectNode.get("ensembles");
        for (JsonNode ensemble : ensemble_arr) {
            Ensemble newEnsemble = new Ensemble();
            newEnsemble.setType(ensemble.get("type").asText());
            ensembles.add(newEnsemble);
        }
        ensembles.get(0).setDistrictPlans(districtPlans);
        ensembles.get(0).setBoxAndWhiskers(boxAndWhiskers);
        StateDemographic whiteDemographic=new StateDemographic();
        whiteDemographic.setType(InterestType.CAUCASIAN);
        whiteDemographic.setPopulation(objectNode.get("caucasian").asInt());
        StateDemographic africanDemographic=new StateDemographic();
        africanDemographic.setType(InterestType.AFRICAN);
        africanDemographic.setPopulation(objectNode.get("africanAmerican").asInt());
        StateDemographic asianDemographic=new StateDemographic();
        asianDemographic.setType(InterestType.ASIAN);
        asianDemographic.setPopulation(objectNode.get("asian").asInt());
        StateDemographic latinoDemographic=new StateDemographic();
        latinoDemographic.setType(InterestType.LATINO);
        latinoDemographic.setPopulation(objectNode.get("latino").asInt());
        List<StateDemographic> stateDemographics = new ArrayList<>(Arrays.asList(whiteDemographic, africanDemographic, asianDemographic, latinoDemographic));

        State newState=new State(objectNode.get("state").asText());
        newState.setStateDemographics(stateDemographics);
        newState.setEnsembles(ensembles);
        return stateService.addState(newState);
    }
}
