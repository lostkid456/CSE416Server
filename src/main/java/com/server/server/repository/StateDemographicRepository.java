package com.server.server.repository;

import com.server.server.model.StateDemographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDemographicRepository extends JpaRepository<StateDemographic,Integer> {
}
