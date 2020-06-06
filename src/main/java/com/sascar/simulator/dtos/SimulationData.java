package com.sascar.simulator.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationData {
    private int vehicles;
    private int online;
    private int offline;
    private int positions;
    private long id;
}