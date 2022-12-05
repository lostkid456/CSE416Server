package com.server.server.repository;

import com.server.server.model.DistrictDemographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDemographicRepository extends JpaRepository<DistrictDemographic,Integer> {
}
