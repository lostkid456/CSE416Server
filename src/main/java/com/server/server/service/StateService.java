package com.server.server.service;

import com.server.server.model.State;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public State getStateHome(String state){
        return stateRepository.findByStateEnsemble_State(state);
    }



}
