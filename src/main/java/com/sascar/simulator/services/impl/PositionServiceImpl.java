package com.sascar.simulator.services.impl;

import java.util.List;

import com.sascar.simulator.entities.Position;
import com.sascar.simulator.repositories.PositionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl {
    private final PositionRepository positionRepository;

    public void save(final Position position) {
        this.positionRepository.save(position);
    }

    public void saveAll(final List<Position> positions) {
        this.positionRepository.saveAll(positions);
    }
}