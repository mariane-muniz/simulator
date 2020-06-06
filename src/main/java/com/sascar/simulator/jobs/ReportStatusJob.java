package com.sascar.simulator.jobs;

import com.sascar.simulator.dtos.SimulationData;
import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.services.MessagingService;
import com.sascar.simulator.services.SimulationService;

import com.sascar.simulator.services.VehicleService;
import com.sascar.simulator.services.impl.PositionServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ReportStatusJob {
    private final MessagingService messagingService;
    private final SimulationService SimulationService;
    private final VehicleService vehicleService;
    private final PositionServiceImpl positionService;

    @Scheduled(fixedDelay = 7000)
    private void notifySocket() {
        final Iterable<Simulation> simulations = this.SimulationService.getSimulations();
        final long count = StreamSupport.stream(simulations.spliterator(), false).count();
        if (count < 1) return;

        simulations.forEach(simulation -> {
            if (simulation.isStatus()) {
                final Long id = simulation.getId();
                final long onlineTotal = this.vehicleService.getVehicleTotalByStatus(true, id);
                final long offlineTotal = this.vehicleService.getVehicleTotalByStatus(false, id);
                final int positionTotal = this.positionService.countPositionsBySimulation(simulation);
                final SimulationData simulationData = new SimulationData();
                simulationData.setId(simulation.getId());
                simulationData.setVehicles(Math.toIntExact(onlineTotal + offlineTotal));
                simulationData.setPositions(positionTotal);
                simulationData.setOffline(Math.toIntExact(onlineTotal));
                simulationData.setOffline(Math.toIntExact(offlineTotal));
                this.messagingService.sendReport(simulationData);
            }
        });
    }
}