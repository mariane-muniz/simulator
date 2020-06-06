package com.sascar.simulator.services;

import java.util.Optional;

import com.sascar.simulator.entities.Simulation;

public interface SimulationService {
    Iterable<Simulation> getSimulations();
    Simulation save(final Simulation simulation);
    void delete(final long id);
    Optional<Simulation> getSimulation(final long id);
}