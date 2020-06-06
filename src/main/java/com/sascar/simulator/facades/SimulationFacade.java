package com.sascar.simulator.facades;

import java.util.Optional;

import com.sascar.simulator.entities.Simulation;

public interface SimulationFacade {
    Iterable<Simulation> getSimulations();
    Simulation save(final Simulation simulation);
    void delete(final long id);
    Optional<Simulation> getSimulation(final long id);
}