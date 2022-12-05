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
        List<Demographic> demographicList=new ArrayList<>();
        Demographic demoAsian=new Demographic();
        demoAsian.setType(InterestType.ASIAN);
        demoAsian.setPopulation(objectNode.get("asian").asInt());
        Demographic demoCaucasian=new Demographic();
        demoCaucasian.setType(InterestType.CAUCASIAN);
        demoCaucasian.setPopulation(objectNode.get("caucasian").asInt());
        Demographic demoAfrican=new Demographic();
        demoAfrican.setType(InterestType.AFRICAN);
        demoAfrican.setPopulation(objectNode.get("africanAmerican").asInt());
        Demographic demoLatino=new Demographic();
        demoLatino.setType(InterestType.LATINO);
        demoLatino.setPopulation(objectNode.get("latino").asInt());
        demographicList.add(demoAsian);
        demographicList.add(demoCaucasian);
        demographicList.add(demoAfrican);
        demographicList.add(demoLatino);

        StateDemographic stateDemographic=new StateDemographic();
        stateDemographic.setDemographics(demographicList);

        List<District> districts1=new ArrayList<>();
        JsonNode dist_arr=objectNode.get("districts1");
        for (JsonNode dNode : dist_arr) {
            District district = new District();
            List<Demographic> demographics = new ArrayList<>();
            Demographic asian = new Demographic();
            demoAsian.setType(InterestType.ASIAN);
            demoAsian.setPopulation(dNode.get("asian").asInt());
            Demographic caucasian = new Demographic();
            demoCaucasian.setType(InterestType.CAUCASIAN);
            demoCaucasian.setPopulation(dNode.get("caucasian").asInt());
            Demographic african = new Demographic();
            demoAfrican.setType(InterestType.AFRICAN);
            demoAfrican.setPopulation(dNode.get("africanAmerican").asInt());
            Demographic latino = new Demographic();
            demoLatino.setType(InterestType.LATINO);
            demoLatino.setPopulation(dNode.get("latino").asInt());
            demographicList.add(asian);
            demographicList.add(caucasian);
            demographicList.add(african);
            demographicList.add(latino);
            DistrictDemographic districtDemographic = new DistrictDemographic();
            districtDemographic.setDemographics(demographics);
            district.setDistrictDemographic(districtDemographic);
            district.setNumber(dNode.get("number").asInt());
            districts1.add(district);
        }
        List<District> districts2=new ArrayList<>();
        JsonNode dist_arr1=objectNode.get("district2");
        for (JsonNode dNode : dist_arr1) {
            District district = new District();
            List<Demographic> demographics = new ArrayList<>();
            Demographic asian = new Demographic();
            demoAsian.setType(InterestType.ASIAN);
            demoAsian.setPopulation(dNode.get("asian").asInt());
            Demographic caucasian = new Demographic();
            demoCaucasian.setType(InterestType.CAUCASIAN);
            demoCaucasian.setPopulation(dNode.get("caucasian").asInt());
            Demographic african = new Demographic();
            demoAfrican.setType(InterestType.AFRICAN);
            demoAfrican.setPopulation(dNode.get("africanAmerican").asInt());
            Demographic latino = new Demographic();
            demoLatino.setType(InterestType.LATINO);
            demoLatino.setPopulation(dNode.get("latino").asInt());
            demographicList.add(asian);
            demographicList.add(caucasian);
            demographicList.add(african);
            demographicList.add(latino);
            DistrictDemographic districtDemographic = new DistrictDemographic();
            districtDemographic.setDemographics(demographics);
            district.setDistrictDemographic(districtDemographic);
            district.setNumber(dNode.get("number").asInt());
            districts2.add(district);
        }
        List<District> districts3=new ArrayList<>();
        JsonNode dist_arr2=objectNode.get("district3");
        for (JsonNode dNode : dist_arr2) {
            District district = new District();
            List<Demographic> demographics = new ArrayList<>();
            Demographic asian = new Demographic();
            demoAsian.setType(InterestType.ASIAN);
            demoAsian.setPopulation(dNode.get("asian").asInt());
            Demographic caucasian = new Demographic();
            demoCaucasian.setType(InterestType.CAUCASIAN);
            demoCaucasian.setPopulation(dNode.get("caucasian").asInt());
            Demographic african = new Demographic();
            demoAfrican.setType(InterestType.AFRICAN);
            demoAfrican.setPopulation(dNode.get("africanAmerican").asInt());
            Demographic latino = new Demographic();
            demoLatino.setType(InterestType.LATINO);
            demoLatino.setPopulation(dNode.get("latino").asInt());
            demographicList.add(asian);
            demographicList.add(caucasian);
            demographicList.add(african);
            demographicList.add(latino);
            DistrictDemographic districtDemographic = new DistrictDemographic();
            districtDemographic.setDemographics(demographics);
            district.setDistrictDemographic(districtDemographic);
            district.setNumber(dNode.get("number").asInt());
            districts3.add(district);
        }
        List<District> districts4=new ArrayList<>();
        JsonNode dist_arr3=objectNode.get("district4");
        for (JsonNode dNode : dist_arr3) {
            District district = new District();
            List<Demographic> demographics = new ArrayList<>();
            Demographic asian = new Demographic();
            demoAsian.setType(InterestType.ASIAN);
            demoAsian.setPopulation(dNode.get("asian").asInt());
            Demographic caucasian = new Demographic();
            demoCaucasian.setType(InterestType.CAUCASIAN);
            demoCaucasian.setPopulation(dNode.get("caucasian").asInt());
            Demographic african = new Demographic();
            demoAfrican.setType(InterestType.AFRICAN);
            demoAfrican.setPopulation(dNode.get("africanAmerican").asInt());
            Demographic latino = new Demographic();
            demoLatino.setType(InterestType.LATINO);
            demoLatino.setPopulation(dNode.get("latino").asInt());
            demographicList.add(asian);
            demographicList.add(caucasian);
            demographicList.add(african);
            demographicList.add(latino);
            DistrictDemographic districtDemographic = new DistrictDemographic();
            districtDemographic.setDemographics(demographics);
            district.setDistrictDemographic(districtDemographic);
            district.setNumber(dNode.get("number").asInt());
            districts4.add(district);
        }

        JsonNode arr=objectNode.get("districtPlans");
        Iterator<JsonNode> itr1=arr.iterator();
        List<DistrictPlan> districtPlans= new ArrayList<>();
        while(itr1.hasNext()){
            JsonNode item= itr1.next();
            DistrictPlan districtPlan=new DistrictPlan();
            districtPlan.setMMD(item.get("isMMD").asBoolean());
            districtPlan.setNumberOfDemocrats(item.get("democrat").asInt());
            districtPlan.setNumberOfRepublicans(item.get("republican").asInt());
            districtPlan.setNumberOfMajorityMinority(item.get("majorityMinority").asInt());
            districtPlan.setDistrictBoundaryPath(item.get("districtBoundaryPath").asText());
            districtPlans.add(districtPlan);
        }

        List<RepDemSplit> splits=new ArrayList<>();
        JsonNode repD=objectNode.get("repD");
        Iterator<JsonNode> itrr=repD.iterator();
        while(itrr.hasNext()){
            JsonNode rpSplit=itrr.next();
            RepDemSplit split=new RepDemSplit();
            split.setSplit(rpSplit.get("split").asText());
            split.setNumberOfPlans(rpSplit.get("number").asInt());
            splits.add(split);
        }
        System.out.println(districtPlans);
        districtPlans.get(0).setDistricts(districts1);
        districtPlans.get(0).setRepDemSplits(splits);
        districtPlans.get(1).setDistricts(districts2);
        districtPlans.get(2).setDistricts(districts3);
        districtPlans.get(3).setDistricts(districts4);

        List<BoxAndWhisker> boxAndWhiskers=new ArrayList<>();
        JsonNode bwNode=objectNode.get("boxW");
        Iterator<JsonNode> bwIterator=bwNode.iterator();
        while(bwIterator.hasNext()){
            JsonNode item=bwIterator.next();
            BoxAndWhisker boxAndWhisker=new BoxAndWhisker();
            boxAndWhisker.setMin(item.get("min").asDouble());
            boxAndWhisker.setMax(item.get("max").asDouble());
            boxAndWhisker.setMedian(item.get("median").asDouble());
            boxAndWhisker.setFirstQ(item.get("firstQ").asDouble());
            boxAndWhisker.setThirdQ(item.get("thirdQ").asDouble());
            boxAndWhisker.setType(InterestType.valueOf(item.get("type").asText()));
            boxAndWhiskers.add(boxAndWhisker);
        }

        Ensemble ensemble=new Ensemble();
        ensemble.setDistrictPlans(districtPlans);
        ensemble.setBoxAndWhiskers(boxAndWhiskers);

        State newState=new State(objectNode.get("state").asText());
        newState.setEnsemble(ensemble);
        newState.setStateDemographic(stateDemographic);

        return stateService.addState(newState);
    }

}
