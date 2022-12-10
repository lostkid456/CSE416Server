package com.server.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.opencsv.CSVReader;
import com.server.server.model.*;
import com.server.server.model.enums.InterestType;
import com.server.server.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
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

    @GetMapping("/smd/graphs/{state}")
    public Ensemble getSMDGraphs(@PathVariable String state){
        State currState=stateService.getState(state);
        Ensemble smd=currState.getEnsembles().get(0);
        return smd;
    }

    @GetMapping("/mmd/graphs/{state}")
    public Ensemble getMMDGraphs(@PathVariable String state){
        State currState=stateService.getState(state);
        Ensemble mmd=currState.getEnsembles().get(1);
        return mmd;
    }

    @PostMapping("/addState")
    public State addState(@RequestBody ObjectNode objectNode){
        State newState = new State();

        List<Ensemble> ensembles = new ArrayList<>();
        Ensemble smd = new Ensemble();
        Ensemble mmd = new Ensemble();

        List<StateDemographic> stateDemographics = new ArrayList<>();

        List<DistrictPlan> smdDistrictPlans=new ArrayList<>();
        List<BoxAndWhisker> smdBoxAndWhiskers=new ArrayList<>();
        List<RepDemSplit> smdRepDemSplits=new ArrayList<>();

        DistrictPlan enactedPlan=new DistrictPlan();
        enactedPlan.setMMD(false);
        enactedPlan.setPattern("smd");
        enactedPlan.setSplit("");
        enactedPlan.setPlanType("smd/enacted");
        List<District> enactedDistricts=new ArrayList<>();

        CSVReader csvReader;
        String[] line;


        String state=objectNode.get("state").asText();
        try {
            int total,caucasian,asian,african,latino,republican,democrat,majorityMinority;
            total=0;
            caucasian=0;
            asian=0;
            african=0;
            latino=0;
            republican=0;
            democrat=0;
            majorityMinority=0;
            if (state.equals("Ohio")) {
                csvReader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/enacted/Ohio/" +
                                                                                       "Ohio_Enacted_Demographics.csv")));
                csvReader.skip(1);
                while((line=csvReader.readNext())!=null){
                    District district=new District();
                    district.setNumber(Integer.parseInt(line[0]));
                    DistrictDemographic caucasianDemo=new DistrictDemographic();
                    caucasianDemo.setType(InterestType.CAUCASIAN);
                    caucasianDemo.setPopulation(Integer.parseInt(line[2]));
                    DistrictDemographic asianDemo=new DistrictDemographic();
                    asianDemo.setType(InterestType.ASIAN);
                    asianDemo.setPopulation(Integer.parseInt(line[3]));
                    DistrictDemographic africanDemo=new DistrictDemographic();
                    africanDemo.setType(InterestType.AFRICAN);
                    africanDemo.setPopulation(Integer.parseInt(line[4]));
                    DistrictDemographic latinoDemo=new DistrictDemographic();
                    latinoDemo.setType(InterestType.LATINO);
                    latinoDemo.setPopulation(Integer.parseInt(line[5]));
                    List<DistrictDemographic> districtDemographics=new ArrayList<>();
                    Collections.addAll(districtDemographics,caucasianDemo,asianDemo,africanDemo,latinoDemo);
                    district.setDistrictDemographics(districtDemographics);
                    total+=Integer.parseInt(line[1]);
                    caucasian+=Integer.parseInt(line[2]);
                    asian+=Integer.parseInt(line[3]);
                    african+=Integer.parseInt(line[4]);
                    latino+=Integer.parseInt(line[5]);
                    democrat+=Integer.parseInt(line[8]);
                    republican+=Integer.parseInt(line[9]);
                    majorityMinority=Integer.parseInt(line[10]);
                    enactedDistricts.add(district);
                }
                enactedPlan.setDistricts(enactedDistricts);
                enactedPlan.setNumberOfRepublican(republican);
                enactedPlan.setNumberOfDemocrat(democrat);
                enactedPlan.setNumberOfMajorityMinority(majorityMinority);
                smdDistrictPlans.add(enactedPlan);
                enactedPlan.setDistrictBoundaryPath("smd/enacted/Ohio/oh_cong_adopted_2022.json");

//                File dir=new File(ResourceUtils.getFile(""))

                newState.setState("OH");
            } else if (state.equals("NorthCarolina")) {
                csvReader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/enacted/NorthCarolina/" +
                                                                                       "NorthCarolina_Enacted_Demographics.csv")));
                csvReader.skip(1);
                while((line=csvReader.readNext())!=null){
                    District district=new District();
                    district.setNumber(Integer.parseInt(line[0]));
                    DistrictDemographic caucasianDemo=new DistrictDemographic();
                    caucasianDemo.setType(InterestType.CAUCASIAN);
                    caucasianDemo.setPopulation(Integer.parseInt(line[2]));
                    DistrictDemographic asianDemo=new DistrictDemographic();
                    asianDemo.setType(InterestType.ASIAN);
                    asianDemo.setPopulation(Integer.parseInt(line[3]));
                    DistrictDemographic africanDemo=new DistrictDemographic();
                    africanDemo.setType(InterestType.AFRICAN);
                    africanDemo.setPopulation(Integer.parseInt(line[4]));
                    DistrictDemographic latinoDemo=new DistrictDemographic();
                    latinoDemo.setType(InterestType.LATINO);
                    latinoDemo.setPopulation(Integer.parseInt(line[5]));
                    List<DistrictDemographic> districtDemographics=new ArrayList<>();
                    Collections.addAll(districtDemographics,caucasianDemo,asianDemo,africanDemo,latinoDemo);
                    district.setDistrictDemographics(districtDemographics);
                    total+=Integer.parseInt(line[1]);
                    caucasian+=Integer.parseInt(line[2]);
                    asian+=Integer.parseInt(line[3]);
                    african+=Integer.parseInt(line[4]);
                    latino+=Integer.parseInt(line[5]);
                    democrat+=Integer.parseInt(line[8]);
                    republican+=Integer.parseInt(line[9]);
                    majorityMinority=Integer.parseInt(line[10]);
                    enactedDistricts.add(district);
                }
                enactedPlan.setDistricts(enactedDistricts);
                enactedPlan.setNumberOfRepublican(republican);
                enactedPlan.setNumberOfDemocrat(democrat);
                enactedPlan.setNumberOfMajorityMinority(majorityMinority);
                smdDistrictPlans.add(enactedPlan);
                enactedPlan.setDistrictBoundaryPath("smd/enacted/NorthCarolina/NC_SMmap2_Statewide.json");

                File dir = new File(ResourceUtils.getFile("classpath:smd/geoJson/NorthCarolina").getAbsolutePath());
                System.out.println(dir.getPath());
                File[] directoryListing=dir.listFiles();
                for(File file:directoryListing){
                    String name= file.getName();
                    DistrictPlan districtPlan=new DistrictPlan();
//                    ObjectMapper objectMapper
                }


                newState.setState("NC");
            } else {
                csvReader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/enacted/Virginia/" +
                                                                                       "Virginia_Enacted_Demographic.csv")));
                csvReader.skip(1);
                while((line=csvReader.readNext())!=null){
                    District district=new District();
                    district.setNumber(Integer.parseInt(line[0]));
                    DistrictDemographic caucasianDemo=new DistrictDemographic();
                    caucasianDemo.setType(InterestType.CAUCASIAN);
                    caucasianDemo.setPopulation(Integer.parseInt(line[2]));
                    DistrictDemographic asianDemo=new DistrictDemographic();
                    asianDemo.setType(InterestType.ASIAN);
                    asianDemo.setPopulation(Integer.parseInt(line[3]));
                    DistrictDemographic africanDemo=new DistrictDemographic();
                    africanDemo.setType(InterestType.AFRICAN);
                    africanDemo.setPopulation(Integer.parseInt(line[4]));
                    DistrictDemographic latinoDemo=new DistrictDemographic();
                    latinoDemo.setType(InterestType.LATINO);
                    latinoDemo.setPopulation(Integer.parseInt(line[5]));
                    List<DistrictDemographic> districtDemographics=new ArrayList<>();
                    Collections.addAll(districtDemographics,caucasianDemo,asianDemo,africanDemo,latinoDemo);
                    district.setDistrictDemographics(districtDemographics);
                    total+=Integer.parseInt(line[1]);
                    caucasian+=Integer.parseInt(line[2]);
                    asian+=Integer.parseInt(line[3]);
                    african+=Integer.parseInt(line[4]);
                    latino+=Integer.parseInt(line[5]);
                    democrat+=Integer.parseInt(line[8]);
                    republican+=Integer.parseInt(line[9]);
                    majorityMinority=Integer.parseInt(line[10]);
                    enactedDistricts.add(district);
                }
                enactedPlan.setDistricts(enactedDistricts);
                enactedPlan.setNumberOfRepublican(republican);
                enactedPlan.setNumberOfDemocrat(democrat);
                enactedPlan.setNumberOfMajorityMinority(majorityMinority);
                smdDistrictPlans.add(enactedPlan);
                enactedPlan.setDistrictBoundaryPath("smd/enacted/Virginia/SCV FINAL CD.json");



                newState.setState("VA");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        smdDistrictPlans.add(enactedPlan);
        smd.setDistrictPlans(smdDistrictPlans);
        ensembles.add(smd);
        ensembles.add(mmd);
        newState.setEnsembles(ensembles);
        newState.setStateDemographics(stateDemographics);
        return stateService.addState(newState);
    }



}
