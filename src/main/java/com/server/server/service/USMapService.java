package com.server.server.service;

import com.server.server.model.map.USMap;
import com.server.server.repository.USMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class USMapService {
    @Autowired
    private USMapRepository usMapRepository;

    public List<USMap> getUSMap(){
        return usMapRepository.findAll();
    }

    public USMap addUSMap(USMap usMap){
        return usMapRepository.save(usMap);
    }

}
