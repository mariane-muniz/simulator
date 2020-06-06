package com.sascar.simulator.populator;

import com.sascar.simulator.dtos.SignalData;
import com.sascar.simulator.entities.Position;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SignalPopulator {

    public SignalData populate(final Position position) {
        final SignalData data = new SignalData();
        data.setVehicle(Math.toIntExact(position.getVehicle().getId()));
        data.setRiskManager(Math.toIntExact(position.getSimulation().getId()));
        this.setDate(position, data);
        return data;
    }

    private void setDate(final Position position, final SignalData data) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final Date date = position.getCreatedAt();
        String stringDate = df.format(date);
        data.setDate(stringDate);
    }
}
