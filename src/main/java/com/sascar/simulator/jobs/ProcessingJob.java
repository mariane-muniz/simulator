package com.sascar.simulator.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sascar.simulator.entities.Position;
import com.sascar.simulator.entities.Vehicle;
import com.sascar.simulator.services.MockDateService;
import com.sascar.simulator.services.VehicleService;
import com.sascar.simulator.services.impl.PositionServiceImpl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessingJob {
    private final MockDateService MockDateService;
    private final PositionServiceImpl PositionService;
    private final VehicleService VehicleService;
    
    @Scheduled(fixedDelay = 5000) 
    private void processing() {
        final List<Position> positions = new ArrayList<>();
        final List<Vehicle> vehicles = this.VehicleService.getDelayeVehicles(new Date());
        vehicles.iterator().forEachRemaining(vehicle -> {
            final Date newDate = this.MockDateService.getDate(120, vehicle.getSendDate());
            final Position position = this.createPosition(vehicle);
            vehicle.setSendDate(newDate);
            positions.add(position);
        });

        log.debug("Positions created: " + positions.size());
        this.VehicleService.saveAll(vehicles);
        this.PositionService.saveAll(positions);
    }

    private Position createPosition(final Vehicle vehicle) {
        Position position = new Position();
        position.setVehicle(vehicle);
        position.setSimulationId(vehicle.getSimulation());
        return position;
    }
}