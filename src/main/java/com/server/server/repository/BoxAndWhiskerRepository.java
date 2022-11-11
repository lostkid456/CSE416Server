package com.server.server.repository;

import com.server.server.model.BoxAndWhisker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxAndWhiskerRepository extends JpaRepository<BoxAndWhisker,Integer> {
}
