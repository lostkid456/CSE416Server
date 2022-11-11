package com.server.server.service;

import com.server.server.model.Ensemble;
import com.server.server.repository.EnsembleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnsembleService {
    @Autowired
    private EnsembleRepository ensembleRepository;

    public Ensemble addEnsemble(String state){
        String filepath="";
        switch(state){
            case "OH":
                filepath="classpath:geoJson/oh_cong_adopted_2022.json";
                break;
            case "FL":
                filepath="classpath:geoJson/P000C0109.json";
                break;
            case "NC":
                filepath="classpath:geoJson/NC_SMmap2_Statewide.json";
                break;
            default:
                break;
        }
        return ensembleRepository.save(new Ensemble(filepath));
    }
}
