package com.server.server.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.Ensemble;
import com.server.server.model.State;
import com.server.server.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StateController {
    @Autowired
    private StateService stateService;

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
    public Optional<State> getState(@PathVariable String state){
       return stateService.getState(state);
    }

    @PostMapping("/addensemble")
    public Ensemble addEnsemble(@RequestBody String state){
        return stateService.addEnsemble(state);
    }

    @PostMapping("/addstate")
    public State addState(@RequestBody String state){
        State newState=new State(state);
        newState.setStateEnsemble(stateService.getEnsemble(state).get());
        return stateService.addState(newState);
    }

}
