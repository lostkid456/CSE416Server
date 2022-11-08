package com.server.server.controller;

import com.server.server.model.State;
import com.server.server.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping("home/{state}")
    public State getState(){
        return new State();
    }

}
