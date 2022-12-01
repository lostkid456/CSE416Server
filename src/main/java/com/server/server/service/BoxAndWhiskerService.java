package com.server.server.service;

import com.server.server.model.BoxAndWhisker;
import com.server.server.repository.BoxAndWhiskerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxAndWhiskerService {
    @Autowired
    private BoxAndWhiskerRepository boxAndWhiskerRepository;


}
