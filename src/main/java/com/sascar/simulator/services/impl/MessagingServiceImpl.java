package com.sascar.simulator.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sascar.simulator.dtos.SignalData;
import com.sascar.simulator.dtos.SimulationData;
import com.sascar.simulator.services.MessagingService;

import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService {
    private final SimpMessagingTemplate template;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendReport(final SimulationData simulationData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(simulationData);
            template.convertAndSend("/report/" + simulationData.getId(), message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendReport(final SignalData position, final long simulationId) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(position);
            template.convertAndSend("/topic/" + simulationId, message);
            log.debug("Send message to: " + simulationId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public void sendMessage(final SignalData position) {
        final ObjectMapper mapper = new ObjectMapper();
        final String message = mapper.writeValueAsString(position);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send("topicposition", message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}