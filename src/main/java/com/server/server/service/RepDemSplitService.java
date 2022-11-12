package com.server.server.service;

import com.server.server.model.RepDemSplit;
import com.server.server.repository.RepDemSplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepDemSplitService {
    @Autowired
    private RepDemSplitRepository repDemSplitRepository;

    public RepDemSplit addRepDemSplit(String split,int plan){
        RepDemSplit newSplit=new RepDemSplit();
        newSplit.setSplit(split);
        newSplit.setNumberOfPlans(plan);
        return repDemSplitRepository.save(newSplit);
    }

    public List<RepDemSplit> addRepDemSplits(List<RepDemSplit> splits){
        return repDemSplitRepository.saveAllAndFlush(splits);
    }
}
