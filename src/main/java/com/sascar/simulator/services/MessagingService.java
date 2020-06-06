package com.sascar.simulator.services;

import com.sascar.simulator.dtos.SimulationData;

public interface MessagingService {
    void send(final SimulationData simulationData);
}