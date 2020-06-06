package com.sascar.simulator.facades.impl;

import java.util.Optional;

import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.facades.SimulationFacade;
import com.sascar.simulator.services.SimulationService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimulationFacadeImpl implements SimulationFacade {
    private final SimulationService simulationService;

    @Override
    public Iterable<Simulation> getSimulations() {
        return this.simulationService.getSimulations();
    }

    @Override
    public Simulation save(final Simulation simulation) {
        Assert.notNull(simulation, "'simulation' cannot be null.");
        return this.simulationService.save(simulation);
    }

    @Override
    public void delete(final long id) {
        Assert.notNull(id, "'id' cannot be null.");
        this.simulationService.delete(id);
    }

    @Override
    public Optional<Simulation> getSimulation(final long id) {
        Assert.notNull(id, "'id' cannot be null");
        return this.simulationService.getSimulation(id);
    }
}