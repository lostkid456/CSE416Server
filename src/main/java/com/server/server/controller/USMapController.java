package com.server.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.model.map.USMap;
import com.server.server.service.USMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class USMapController {
    @Autowired
    private USMapService usMapService;
    @Value("classpath:geoJson/tl_2022_us_state.json")
    private Resource resource;
    @GetMapping("/home")
    public List<USMap> getUSMap(){
        List<USMap> usMapList=usMapService.getUSMap();
        if(usMapList.size()==0){
            try{
                ObjectMapper objectMapper=new ObjectMapper();
                USMap usMap=objectMapper.readValue(resource.getFile(), USMap.class);
                usMapService.addUSMap(usMap);
                usMapList.add(usMap);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        return  usMapList;
    }
}
