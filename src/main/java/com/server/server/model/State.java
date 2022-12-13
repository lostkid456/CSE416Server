package com.server.server.model;

import com.server.server.model.enums.InterestType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    public List<Object> getStateDemographics(){
        List<Object> objects=new ArrayList<>();
        double percentage=0;
        LinkedHashMap<InterestType,Double> percentages=new LinkedHashMap<>();
        LinkedHashMap<InterestType,Double> populations=new LinkedHashMap<>();
        int stateTotal=totalPopulation;
        for(StateDemographic stateDemographic:stateDemographics){
            Double population= (double) stateDemographic.getPopulation();
            percentage+=population/stateTotal;
            populations.put(stateDemographic.getType(),population);
            percentages.put(stateDemographic.getType(), 100*(population/stateTotal));
        }
        if(1-percentage<0){
            percentages.put(InterestType.OTHER,0.0);
        }else{
            percentages.put(InterestType.OTHER,100*(1-percentage));
        }
        objects.add(percentages);
        objects.add(populations);
        return objects;
    }
}
