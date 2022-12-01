package com.server.server.service;

import com.server.server.model.Ensemble;
import com.server.server.repository.EnsembleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnsembleService {
    @Autowired
    private EnsembleRepository ensembleRepository;


}
