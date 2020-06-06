package com.sascar.simulator.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.repositories.SimulationRepository;
import com.sascar.simulator.services.SimulationService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService {
    private final SimulationRepository simulationRepository;

    @Override
    public Iterable<Simulation> getSimulations() {
        return this.simulationRepository.findAll();
    }

    @Override
    public Simulation save(final Simulation simulation) {
        return this.simulationRepository.save(simulation);
    }

    @Override
    public void delete(final long id) {
        Assert.notNull(id, "'id' cannot be null");
        this.simulationRepository.deleteById(id);
    }

    @Override
    public Optional<Simulation> getSimulation(final long id) {
        Assert.notNull(id, "'id' cannot be null");
        return this.simulationRepository.findById(id);
    }
}