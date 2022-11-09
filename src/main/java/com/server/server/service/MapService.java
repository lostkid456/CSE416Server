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

    public Optional<Map> getMap(String state){
        return mapRepository.findById(state);
    }

    public Map addMap(Map map){
        return mapRepository.save(map);
    }


}
