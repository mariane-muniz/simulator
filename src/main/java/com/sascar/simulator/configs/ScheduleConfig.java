package com.sascar.simulator.configs;

import com.sascar.simulator.entities.Vehicle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ScheduleConfig {
    private HashMap<Long, Integer> sendPositionCounter;
    private HashMap<Long, List<Vehicle>> activeVehicles;
    private HashMap<Long, List<Vehicle>> pausedVehicles;

    {
        this.sendPositionCounter = new HashMap<>();
        this.activeVehicles = new HashMap<>();
        this.pausedVehicles = new HashMap<>();
    }

    @Bean
    public HashMap<Long, Integer> sendPositionCounter() {
        return sendPositionCounter;
    }

    @Bean
    public HashMap<Long, List<Vehicle>> activeVehicles() {
        return activeVehicles;
    }

    @Bean
    public HashMap<Long, List<Vehicle>> pausedVehicles() {
        return pausedVehicles;
    }
}