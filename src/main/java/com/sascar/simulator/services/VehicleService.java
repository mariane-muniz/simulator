package com.sascar.simulator.services;

import java.util.Date;
import java.util.List;

import com.sascar.simulator.entities.Vehicle;

public interface VehicleService {    
    void save(final Vehicle vehicle);
    void delete(final List<Vehicle> vehicles);
    void saveAll(final List<Vehicle> vehicles);
    List<Vehicle> getDelayedVehicles(final Date date);

    long getVehicleTotalByStatus(boolean status, long simulationId);
}