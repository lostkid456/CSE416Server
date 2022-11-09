package com.server.server.repository;

import com.server.server.model.State;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StateRepository extends JpaRepository<State,String> {
}
