package com.sascar.simulator.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.entities.Vehicle;
import com.sascar.simulator.services.MockDateService;
import com.sascar.simulator.services.SimulationService;
import com.sascar.simulator.services.VehicleService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleManagementJob {
    private final SimulationService simulationService;
    private final MockDateService mockDateService;
    private final VehicleService vehicleService;
    
    @Scheduled(fixedDelay = 10000)
    public void vehicleManagement() {
        log.debug("VehicleManagementJob executed");
        this.simulationService.getSimulations().iterator().forEachRemaining(simulation -> {
            final int fleetSize = simulation.getFleetSize();
            final List<Vehicle> createdVehicles = simulation.getVehicles();
            final int vehicleTotals = createdVehicles.size();
            final Long simulationId = simulation.getId();

            log.debug("Simulation ID: " + simulationId);
            log.debug("Fleet size: " + fleetSize);
            log.debug("Vehicles totals: " + vehicleTotals);

            if (vehicleTotals > fleetSize) 
                this.downFleetSize(fleetSize, createdVehicles);
            else if (vehicleTotals < fleetSize) 
                this.upFleetSize(fleetSize, simulation, createdVehicles);
        });
    }

    private void downFleetSize(
        final int fleetSize,
        final List<Vehicle> createdVehicles) {
        log.debug("Total to remove: " + (createdVehicles.size() - fleetSize));            
        final List<Vehicle> removeList = 
            createdVehicles.subList(fleetSize, createdVehicles.size());
        this.vehicleService.delete(removeList);
        log.debug("New size: " + createdVehicles.size());            
    }

    private void upFleetSize(
        final int fleetSize,
        final Simulation simulation,
        final List<Vehicle> createdVehicles) {
        final int size = createdVehicles.size();
        final int toCreateSize = fleetSize - size;
        final List<Date> dates = 
            this.mockDateService.getListOfDates(1, toCreateSize);
        
        log.debug("Total to add: " + toCreateSize);  
        List<Vehicle> newVehicles = new ArrayList<>();
        
        for(int i=0; i < toCreateSize ; i++) {
            final Vehicle vehicle = new Vehicle();
            vehicle.setActive(false);
            vehicle.setPlate(UUID.randomUUID().toString());
            vehicle.setSendDate(dates.get(i));
            vehicle.setSimulation(simulation);
            newVehicles.add(vehicle);
            if (newVehicles.size() > 500) {
                this.vehicleService.saveAll(newVehicles);
                newVehicles = new ArrayList<>();
            }
        }
        if (newVehicles.size() > 0)
                this.vehicleService.saveAll(newVehicles);
        log.debug("New size: " + simulation.getVehicles().size());
    }
    
}