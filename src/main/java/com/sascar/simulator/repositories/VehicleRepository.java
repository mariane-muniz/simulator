package com.sascar.simulator.repositories;

import java.util.Date;
import java.util.List;

import com.sascar.simulator.entities.Vehicle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Query(value =
        "SELECT v " +
        "FROM Vehicle v " +
        "JOIN Simulation s ON s.id = v.simulation.id " +
        "WHERE s.status = true " +
        "AND v.sendDate < :date"
    )
    List<Vehicle> getDelayedVehicles(@Temporal(TemporalType.TIME) final Date date);

    @Query(value =
            "SELECT count (v) " +
            "FROM Vehicle v " +
            "JOIN Simulation s ON s.id = v.simulation.id " +
            "WHERE v.active = :active " +
            "AND s.id = :id"
    )
    long getVehicleTotalByStatus(@Param("active") final boolean active, @Param("id") final Long id);
}