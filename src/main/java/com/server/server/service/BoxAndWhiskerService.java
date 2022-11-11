package com.server.server.service;

import com.server.server.model.BoxAndWhisker;
import com.server.server.repository.BWTypeRepository;
import com.server.server.repository.BoxAndWhiskerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxAndWhiskerService {
    @Autowired
    private BoxAndWhiskerRepository boxAndWhiskerRepository;
    @Autowired
    private BWTypeRepository bwTypeRepository;

    public BoxAndWhisker addBoxAndWhisker(BoxAndWhisker boxAndWhisker){
        return boxAndWhiskerRepository.save(boxAndWhisker);
    }
}
