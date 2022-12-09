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

}
