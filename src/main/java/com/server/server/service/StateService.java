package com.server.server.service;

import com.server.server.model.State;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository repository;

    public Optional<State> getState(String state){
        return repository.findById(state);
    }

    public State setState(State state){
        return repository.save(state);
    }
}
