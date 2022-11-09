package com.server.server.repository;

import com.server.server.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State,String> {
    public State findByStateEnsemble_State(String state);
}
