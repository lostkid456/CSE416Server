package com.server.server.controller;

import com.fasterxml.jackson.core.type.TypeReference;
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

import javax.swing.*;
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
        enactedPlan.setPlanType("smd/enacted");
        List<District> enactedDistricts=new ArrayList<>();

        String enactedPlanPath;

        CSVReader enactedCsvReader;
        CSVReader boxAndWhiskerCsvReader;
        CSVReader summaryCsvReader;
        CSVReader splitCsvReader;

        String geoJsonPath;

        File geoJsonDir;
        File boxAndWhiskerDir;

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
            if(state.equals("OH")){
                enactedCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/enacted" +
                                                                                            "/Ohio/Ohio_Enacted_Demographics.csv")));
                geoJsonDir=new File(ResourceUtils.getFile("classpath:smd/geoJson/Ohio").getAbsolutePath());
                geoJsonPath="smd/geoJson/Ohio/";
                boxAndWhiskerDir=new File(ResourceUtils.getFile("classpath:smd/boxandwhisker/Ohio").getAbsolutePath());
                enactedPlanPath="smd/enacted/Ohio/oh_cong_adopted_2022.json";
                summaryCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/summary/Ohio/" +
                                                                                            "OH_SMD_Summary.csv")));
                splitCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/split/Ohio/" +
                                                                                          "OH_political_split.csv")));
                newState.setState("OH");
            }else if(state.equals("VA")){
                enactedCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/enacted" +
                                                                                            "/Virginia" +
                                                                                            "/Virginia_Enacted_Demographic.csv")));
                geoJsonDir=new File(ResourceUtils.getFile("classpath:smd/geoJson/Virginia").getAbsolutePath());
                geoJsonPath="smd/geoJson/Virginia/";
                boxAndWhiskerDir=
                        new File(ResourceUtils.getFile("classpath:smd/boxandwhisker/Virginia").getAbsolutePath());
                enactedPlanPath="smd/enacted/Virginia/SCV FINAL CD.json";
                summaryCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/summary/Virginia/" +
                                                                                            "VA_SMD_Summary.csv")));
                splitCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/split/Virginia/" +
                                                                                          "VA_political_split.csv")));
                newState.setState("VA");
            }else{
                enactedCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/enacted" +
                                                                                            "/NorthCarolina" +
                                                                                            "/NorthCarolina_Enacted_Demographics.csv")));
                geoJsonDir=new File(ResourceUtils.getFile("classpath:smd/geoJson/NorthCarolina").getAbsolutePath());
                geoJsonPath="smd/geoJson/NorthCarolina/";
                boxAndWhiskerDir=
                        new File(ResourceUtils.getFile("classpath:smd/boxandwhisker/NorthCarolina").getAbsolutePath());
                enactedPlanPath="smd/enacted/NorthCarolina/NC_SMmap2_Statewide.json";
                summaryCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/summary" +
                                                                                            "/NorthCarolina" +
                                                                                            "/" +
                                                                                            "NC_SMD_Summary.csv")));
                splitCsvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:smd/split/NorthCarolina/" +
                                                                                          "NC_political_split.csv")));
                newState.setState("NC");
            }
            enactedCsvReader.skip(1);
            while((line=enactedCsvReader.readNext())!=null){
                District district=new District();
                district.setNumber(Integer.parseInt(line[0]));
                district.setTotal(Integer.parseInt(line[1]));
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
            StateDemographic whiteD=new StateDemographic();
            whiteD.setType(InterestType.CAUCASIAN);
            whiteD.setPopulation(caucasian);
            StateDemographic asianD=new StateDemographic();
            asianD.setType(InterestType.ASIAN);
            asianD.setPopulation(asian);
            StateDemographic africanD=new StateDemographic();
            africanD.setType(InterestType.AFRICAN);
            africanD.setPopulation(african);
            StateDemographic latinoD=new StateDemographic();
            latinoD.setType(InterestType.LATINO);
            latinoD.setPopulation(latino);
            stateDemographics.add(whiteD);
            stateDemographics.add(asianD);
            stateDemographics.add(africanD);
            stateDemographics.add(latinoD);
            newState.setTotalPopulation(total);
            enactedPlan.setDistricts(enactedDistricts);
            enactedPlan.setNumberOfRepublican(republican);
            enactedPlan.setNumberOfDemocrat(democrat);
            enactedPlan.setNumberOfMajorityMinority(majorityMinority);
            smdDistrictPlans.add(enactedPlan);
            enactedPlan.setDistrictBoundaryPath(enactedPlanPath);
            File[] directoryListing=geoJsonDir.listFiles();
            for(File file:directoryListing){
                String name= file.getName();
                DistrictPlan districtPlan=new DistrictPlan();
                districtPlan.setPattern("smd");
                if(name.contains("extreme_dem")){
                    districtPlan.setPlanType("smd/extreme_dem");
                }else if(name.contains("extreme_rep")){
                    districtPlan.setPlanType("smd/extreme_rep");
                }else if(name.contains("least_majority")){
                    districtPlan.setPlanType("smd/least_majority");
                }else if(name.contains("most_majority")){
                    districtPlan.setPlanType("smd/most_majority");
                }else{
                    districtPlan.setPlanType("smd/random");
                }
                districtPlan.setDistrictBoundaryPath(file.getAbsolutePath());
                List<District> districts=new ArrayList<>();
                ObjectMapper objectMapper=new ObjectMapper();
                Map<String,Object> json=objectMapper.readValue(file,new TypeReference<>(){});
                ArrayList features=(ArrayList)json.get("features");
                int repV=0;
                int demV=0;
                int mMin=0;
                int demSaveTotal=0;
                int repSaveTotal=0;
                String drs="";
                for(Object feature:features){
                    District district=new District();
                    List<DistrictDemographic> demographics=new ArrayList<>();
                    DistrictDemographic whiteDemo=new DistrictDemographic();
                    DistrictDemographic asianDemo=new DistrictDemographic();
                    DistrictDemographic africanDemo=new DistrictDemographic();
                    DistrictDemographic latinoDemo=new DistrictDemographic();
                    LinkedHashMap<String,Object> feat=(LinkedHashMap<String, Object>) feature;
                    LinkedHashMap<String,Object> properties=(LinkedHashMap<String, Object>) feat.get("properties");
                    String districtNum=(String)properties.get("District");
                    Double totalPop=(Double)properties.get("TotalPop");
                    Double whitePop=(Double) properties.get("WhitePop");
                    Double asianPop=(Double)properties.get("AsianPop");
                    Double africanPop=(Double) properties.get("AfricanPop");
                    Double hispPop=(Double) properties.get("HispPop");
                    Double demVote=(Double)properties.get("DemVote");
                    Double repVote=(Double)properties.get("RepVote");
                    String drSplit=(String)properties.get("DRSplit");
                    Double compactness=(Double)properties.get("Compactness");
                    Double majorMin=(Double)properties.get("MajorMin");
//                        Double minorRep=(Double)properties.get("MinorRep");
                    drs=drSplit;
                    mMin=majorMin.intValue();
                    Double demSave=(Double)properties.get("DemSave");
                    Double repSave=(Double)properties.get("RepSave");
                    repV+=repVote;
                    demV+=demVote;
                    demSaveTotal+=demSave;
                    repSaveTotal+=repSave;
                    whiteDemo.setType(InterestType.CAUCASIAN);
                    whiteDemo.setPopulation(whitePop.intValue());
                    asianDemo.setType(InterestType.ASIAN);
                    asianDemo.setPopulation(asianPop.intValue());
                    africanDemo.setType(InterestType.AFRICAN);
                    africanDemo.setPopulation(africanPop.intValue());
                    latinoDemo.setType(InterestType.LATINO);
                    latinoDemo.setPopulation(hispPop.intValue());
                    Collections.addAll(demographics,whiteDemo,asianDemo,africanDemo,latinoDemo);
                    district.setDistrictDemographics(demographics);
                    district.setCompactness(compactness);
                    district.setTotal(totalPop.intValue());
                    district.setNumber(Integer.parseInt(districtNum));
                    district.setDemSafe(demSave.intValue());
                    district.setRepSave(repSave.intValue());
                    districts.add(district);
                }
                districtPlan.setDistricts(districts);
                districtPlan.setMMD(false);
                districtPlan.setNumberOfMajorityMinority(mMin);
                districtPlan.setNumberOfRepublican(repV);
                districtPlan.setNumberOfDemocrat(demV);
//                districtPlan.setSafeDemocrat(demSaveTotal);
//                districtPlan.setSafeRepublican(repSaveTotal);
                districtPlan.setSplit(drs);
                districtPlan.setDistrictBoundaryPath(geoJsonPath+file.getName());
                smdDistrictPlans.add(districtPlan);
            }
            directoryListing=boxAndWhiskerDir.listFiles();
            for(File file:directoryListing){
                String name=file.getName();
                boxAndWhiskerCsvReader=new CSVReader(new FileReader(file.getAbsolutePath()));
                boxAndWhiskerCsvReader.skip(1);
                List<BoxAndWhisker> boxAndWhiskers=new ArrayList<>();
                int counter=0;
                while((line=boxAndWhiskerCsvReader.readNext())!=null){
                    System.out.println("Box and Whisker: "+Arrays.toString(line));
                    if(counter==0){
                        for(int i=1;i<line.length;i++){
                            BoxAndWhisker boxAndWhisker=new BoxAndWhisker();
                            if(name.contains("african")){
                                boxAndWhisker.setType(InterestType.AFRICAN);
                            }else if(name.contains("asian")){
                                boxAndWhisker.setType(InterestType.ASIAN);
                            }else if(name.contains("hispanic")){
                                boxAndWhisker.setType(InterestType.LATINO);
                            }
                            boxAndWhiskers.add(boxAndWhisker);
                        }
                        counter=1;
                    }else{
                        System.out.println("BB :"+Arrays.toString(line));
                        if(line[0].equals("mean")){
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setMean(Double.parseDouble(line[i]));
                            }
                        }else if(line[0].equals("std")){
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setStd(Double.parseDouble(line[i]));
                            }
                        }else if(line[0].equals("25%")){
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setFirstQ(Double.parseDouble(line[i]));
                            }
                        }
                        else if(line[0].equals("min")){
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setMin(Double.parseDouble(line[i]));
                            }
                        }else if(line[0].equals("50%")){
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setMedian(Double.parseDouble(line[i]));
                            }
                        }else if(line[0].equals("75%")){
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setThirdQ(Double.parseDouble(line[i]));
                            }
                        }else{
                            for(int i=1;i<line.length;i++){
                                boxAndWhiskers.get(i-1).setMax(Double.parseDouble(line[i]));
                            }
                        }
                    }
                }
                smdBoxAndWhiskers.addAll(boxAndWhiskers);
            }
            summaryCsvReader.skip(1);
            while((line=summaryCsvReader.readNext())!=null){
                System.out.println("Summary: "+ Arrays.toString(line));
                smd.setAveragePopulation((int) Math.round(Double.parseDouble(line[3])));
                smd.setAverageWhitePopulation((int) Math.round(Double.parseDouble(line[4])));
                smd.setAverageAfricanPopulation((int) Math.round(Double.parseDouble(line[6])));
                smd.setAverageAsianPopulation((int) Math.round(Double.parseDouble(line[5])));
                smd.setAverageLatinoPopulation((int) Math.round(Double.parseDouble(line[7])));
                smd.setAveragePolsbyPopper(Double.parseDouble(line[9]));
                smd.setAverageMajorityMinority((int) Math.round(Double.parseDouble(line[8])));
                smd.setAverageDemocrat((int) Math.round(Double.parseDouble(line[10])));
                smd.setAverageRepublican((int) Math.round(Double.parseDouble(line[11])));
            }
            splitCsvReader.skip(1);
            while((line=splitCsvReader.readNext())!=null){
                System.out.println("Split: "+Arrays.toString(line));
                RepDemSplit repDemSplit=new RepDemSplit();
                repDemSplit.setNumberOfPlan(Integer.parseInt(line[2]));
                repDemSplit.setSplit(line[1]);
                smdRepDemSplits.add(repDemSplit);
            }
            smd.setRepDemSplits(smdRepDemSplits);
            smd.setBoxAndWhiskers(smdBoxAndWhiskers);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        smdDistrictPlans.add(enactedPlan);
        smd.setDistrictPlans(smdDistrictPlans);
        smd.setType("SMD");
        mmd.setType("MMD");
        ensembles.add(smd);
        ensembles.add(mmd);
        newState.setEnsembles(ensembles);
        newState.setStateDemographics(stateDemographics);
        return stateService.addState(newState);
    }



}
