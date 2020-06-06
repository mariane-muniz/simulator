package com.sascar.simulator.repositories;

import com.sascar.simulator.entities.Position;

import com.sascar.simulator.entities.Simulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {
    int countPositionsBySimulation(final Simulation simulation);
}