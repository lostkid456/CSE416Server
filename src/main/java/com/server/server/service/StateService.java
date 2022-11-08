package com.server.server.service;

import com.server.server.model.State;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    @Autowired
    private StateRepository repository;

    public State setState(State state){
        return repository.save(state);
    }
}
