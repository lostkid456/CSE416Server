package com.server.server.repository;

import com.server.server.model.relationships.IsMMD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface isMMDRepository extends JpaRepository<IsMMD,Integer> {
}
