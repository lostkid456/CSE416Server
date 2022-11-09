package com.server.server.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.Ensemble;
import com.server.server.model.State;
import com.server.server.model.map.Map;
import com.server.server.service.MapService;
import com.server.server.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StateController {
    @Autowired
    private StateService stateService;
    @Autowired
    private MapService mapService;

    @GetMapping("/home")
    public Optional<Map> getUSMap(){
        return mapService.getMap("home");
    }

    @GetMapping("/home/{state}")
    public Optional<State> getState(@PathVariable String state){
       return stateService.getState(state);
    }

    @PostMapping("/addmap")
    public Map addMap(@RequestBody String state){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch (state) {
                case "OH":
                    Map oMap = objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/oh_cong_adopted_2022.json"),
                                                      Map.class);
                    oMap.setId("OH");
                    return mapService.addMap(oMap);
                case "FL":
                    Map fMap=objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/P000C0109.json"),
                                                    Map.class);
                    fMap.setId("FL");
                    return mapService.addMap(fMap);
                case "NC":
                    Map nMap=objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/NC_SMmap2_Statewide.json"),
                                                    Map.class);
                    nMap.setId("NC");
                    return mapService.addMap(nMap);
                case "home":
                    Map homeMap=objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/tl_2022_us_state.json"),
                                                       Map.class);
                    homeMap.setId("home");
                    return mapService.addMap(homeMap);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/addstate")
    public State addState(@RequestBody String state){
        Optional<Ensemble> ensemble=stateService.getEnsemble(state);
        State newState=new State();
        newState.setState(state);
        newState.setStateEnsemble(ensemble.get());
        return stateService.addState(newState);
    }

    @PostMapping("/addensemble")
    public Ensemble addEnsemble(@RequestBody String state){
        Optional<Map> stateMap=mapService.getMap(state);
        Ensemble newEnsemble=new Ensemble();
        System.out.println(stateMap.get());
        newEnsemble.setCurrentDistrict(stateMap.get());
        newEnsemble.setState(state);
        return stateService.addEnsemble(newEnsemble);
    }

}
