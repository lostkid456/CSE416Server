package com.server.server.service;

import com.server.server.model.map.Map;
import com.server.server.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapService {
    @Autowired
    private MapRepository mapRepository;

    private Optional<Map> getStateMap(String state){
        return mapRepository.findById(state);
    }

    private Map addStateMap(Map map){
        return mapRepository.save(map);
    };

    public Optional<Map> getUSMap(){
        return mapRepository.findById("home");
    }

    public Map addUSMap(Map map){
        return mapRepository.save(map);
    }

}
