package com.server.server.repository;

import com.server.server.model.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, String> {

}
