package com.sascar.simulator.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sascar.simulator.dtos.SignalData;
import com.sascar.simulator.entities.Position;
import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.entities.Vehicle;
import com.sascar.simulator.populator.SignalPopulator;
import com.sascar.simulator.services.MessagingService;
import com.sascar.simulator.services.MockDateService;
import com.sascar.simulator.services.VehicleService;
import com.sascar.simulator.services.impl.PositionServiceImpl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessingJob {
    private final MockDateService MockDateService;
    private final PositionServiceImpl positionService;
    private final VehicleService vehicleService;
    private final MessagingService messagingService;
    private final SignalPopulator signalPopulator;

    @Scheduled(fixedDelay = 5000)
    private void processing() {
        final List<Position> positions = new ArrayList<>();
        final List<Vehicle> vehicles = this.vehicleService.getDelayedVehicles(new Date());

        if (CollectionUtils.isEmpty(vehicles)) return;

        vehicles.iterator().forEachRemaining(vehicle -> {
            final Position position = this.positionService.createPosition(vehicle);
            final Date newDate = this.MockDateService.getDate(120, vehicle.getSendDate());
            vehicle.setSendDate(newDate);
            positions.add(position);
        });

        log.debug("Positions created: " + positions.size());
        this.vehicleService.saveAll(vehicles);
        this.positionService.saveAll(positions);
        this.sendPosition(positions);
        if (log.isDebugEnabled())
            this.debugPositionsCreated(positions);
    }

    private void sendPosition(final List<Position> positions) {
        positions.parallelStream().forEach(position -> {
            final SignalData data = this.signalPopulator.populate(position);
            this.messagingService.sendReport(data, position.getSimulation().getId());
            this.messagingService.sendMessage(data);
        });
    }

    private void debugPositionsCreated(final List<Position> positions) {
        positions.forEach(p ->
                log.debug("id> {} date> {}", p.getId(), p.getCreatedAt()));
    }
}