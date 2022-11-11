package com.server.server.repository;

import com.server.server.model.Ensemble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsembleRepository extends JpaRepository<Ensemble,Integer> {
}
