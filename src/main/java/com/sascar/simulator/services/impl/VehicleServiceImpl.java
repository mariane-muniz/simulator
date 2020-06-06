package com.sascar.simulator.services.impl;

import java.util.Date;
import java.util.List;

import com.sascar.simulator.entities.Vehicle;
import com.sascar.simulator.repositories.VehicleRepository;
import com.sascar.simulator.services.VehicleService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public void save(final Vehicle vehicle) {
        Assert.notNull(vehicle, "Vehicle cannot be null");
        this.vehicleRepository.save(vehicle);
    }

    @Override
    public void saveAll(final List<Vehicle> vehicles) {
        Assert.notNull(vehicles, "Vehicles cannot be null");
        this.vehicleRepository.saveAll(vehicles);
    }

    @Override
    public void delete(final List<Vehicle> vehicles) {
        Assert.notNull(vehicles, "Vehicles cannot be null");
        this.vehicleRepository.deleteAll(vehicles);
    }

    @Override
    public List<Vehicle> getDelayedVehicles(final Date date) {
        Assert.notNull(date, "date cannot be null");
        return this.vehicleRepository.getDelayedVehicles(date);
    }

    @Override
    public long getVehicleTotalByStatus(final boolean status, final long simulationId) {
        return this.vehicleRepository.getVehicleTotalByStatus(status, simulationId);
    }
}