package com.server.server.repository;

import com.server.server.model.relationships.BWType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BWTypeRepository extends JpaRepository<BWType,Integer> {
}
