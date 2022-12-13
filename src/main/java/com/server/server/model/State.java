package com.server.server.model;

import com.server.server.model.enums.InterestType;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "States")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String state;

    private int totalPopulation;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="state_id")
    private List<Ensemble> ensembles;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="state_id")
    private List<StateDemographic> stateDemographics;

    public LinkedHashMap<InterestType,Double> getStateDemographics(){
        double percentage=0;
        LinkedHashMap<InterestType,Double> percentages=new LinkedHashMap<>();
        int stateTotal=totalPopulation;
        for(StateDemographic stateDemographic:stateDemographics){
            Double population= (double) stateDemographic.getPopulation();
            percentage+=population/stateTotal;
            percentages.put(stateDemographic.getType(), (population/stateTotal));
        }
        if(1-percentage<0){
            percentages.put(InterestType.OTHER,0.0);
        }else{
            percentages.put(InterestType.OTHER,1-percentage);
        }
        return percentages;
    }
}
