package com.sascar.simulator.services.impl;

import java.util.List;

import com.sascar.simulator.entities.Position;
import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.entities.Vehicle;
import com.sascar.simulator.repositories.PositionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl {
    private final PositionRepository positionRepository;

    public void saveAll(final List<Position> positions) {
        this.positionRepository.saveAll(positions);
    }

    public Position createPosition(final Vehicle vehicle) {
        Assert.notNull(vehicle, "'vehicle' cannot be null.");
        final Position position = new Position();
        position.setVehicle(vehicle);
        position.setSimulation(vehicle.getSimulation());
        return position;
    }

    public int countPositionsBySimulation(final Simulation simulationId) {
        return positionRepository.countPositionsBySimulation(simulationId);
    }
}