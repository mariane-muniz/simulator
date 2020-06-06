package com.sascar.simulator.services;

import com.sascar.simulator.dtos.SignalData;
import com.sascar.simulator.dtos.SimulationData;
import lombok.SneakyThrows;

public interface MessagingService {
    void sendReport(SimulationData simulationData);

    void sendReport(SignalData position, long simulationId);

    @SneakyThrows
    void sendMessage(SignalData position);
}