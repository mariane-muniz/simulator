package com.sascar.simulator.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sascar.simulator.dtos.SimulationData;
import com.sascar.simulator.services.MessagingService;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService {
    private final SimpMessagingTemplate template;

    @Override
    public void send(final SimulationData simulationData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(simulationData);
            template.convertAndSend("/topic/" + simulationData.getId(), message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}