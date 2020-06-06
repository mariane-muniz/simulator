package com.sascar.simulator.jobs;

import com.sascar.simulator.dtos.SimulationData;
import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.services.MessagingService;
import com.sascar.simulator.services.SimulationService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportStatusJob {
    private final MessagingService messagingService;
    private final SimulationService SimulationService;

    @Scheduled(fixedDelay = 1000)
    private void notifySocket() {
        final Iterable<Simulation> simulations = this.SimulationService.getSimulations();
        simulations.forEach(simulation -> {
            if (simulation.isStatus()) {
                final SimulationData simulationData = new SimulationData();
                simulationData.setId(simulation.getId());
                simulationData.setVehicles(simulation.getVehicles().size());
                simulationData.setPositions(simulation.getPositions().size());
                this.messagingService.send(simulationData);
            }
        });
    }
}