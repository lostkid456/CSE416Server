package com.server.server.repository;

import com.server.server.model.RepDemSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepDemSplitRepository extends JpaRepository<RepDemSplit,Integer> {
}
