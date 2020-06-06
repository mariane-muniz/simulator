package com.sascar.simulator.repositories;

import java.util.Date;
import java.util.List;

import com.sascar.simulator.entities.Vehicle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Query(
        "SELECT v " +
        "FROM Vehicle v " +
        "JOIN Simulation s ON s.id = v.simulation " +
        "WHERE s.status = 'false'" 
    )
    List<Vehicle> getDelayeVehicles(final Date date);
}