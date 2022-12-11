package com.server.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "DistrictPlans")
public class DistrictPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isMMD;

    private String pattern;

    private String planType;

    private int numberOfDemocrat;

    private int numberOfRepublican;

    private int numberOfMajorityMinority;

    @Transient
    private int total;

    @Transient
    private int safeDemocrat;

    @Transient
    private int safeRepublican;

    private String split;

    private String districtBoundaryPath;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "district_plan_id")
    private List<District> districts;

    public int findTotal() {
        int total=0;
        for(District district:districts){
            total+=district.getTotal();
        }
        return total;
    }

    public int findSafeDemocrat() {
        int dem=0;
        for(District district:districts){
            dem+=district.getDemSafe();
        }
        return dem;
    }

    public int findSafeRepublican() {
        int rep=0;
        for(District district:districts){
            rep+=district.getRepSave();
        }
        return rep;
    }
}
