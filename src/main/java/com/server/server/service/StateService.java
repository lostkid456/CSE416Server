package com.server.server.service;

import com.server.server.model.Ensemble;
import com.server.server.model.State;
import com.server.server.repository.EnsembleRepository;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private EnsembleRepository ensembleRepository;

    public Optional<State> getState(String id){
        return stateRepository.findById(id);
    }

    public State addState(State state){
        return stateRepository.save(state);
    }

    public Optional<Ensemble> getEnsemble(String id){
        return ensembleRepository.findById(id);
    }

    public Ensemble addEnsemble(Ensemble ensemble){
        return ensembleRepository.save(ensemble);
    }


}
