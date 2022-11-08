package com.server.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.State;
import com.server.server.model.map.Map;
import com.server.server.service.MapService;
import com.server.server.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        Optional<Map> map=mapService.getUSMap();
        if(map.isEmpty()){
            try{
                ObjectMapper objectMapper=new ObjectMapper();
                Map usMap =objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/tl_2022_us_state.json"), Map.class);
                usMap.setId("home");
                mapService.addUSMap(usMap);
                return Optional.of(usMap);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        return map;
    }

    @GetMapping("/home/{state}")
    public Optional<State> getState(@PathVariable String state){
        Optional<State> stateInfo=stateService.getState(state);
        if(stateInfo.isEmpty()){
            try{
                ObjectMapper objectMapper=new ObjectMapper();
                Map stateMap;
                switch(state){
                    case "FL":
                        stateMap=objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/P000C0109.json"), Map.class);
                    case "OH":
                        stateMap=objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/oh_cong_adopted_2022.json"), Map.class);
                    case "NC":
                        stateMap=objectMapper.readValue(ResourceUtils.getFile("classpath:geoJson/NC_SMmap2_Statewide.json"), Map.class);
                }

            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return stateInfo;
    }

}
