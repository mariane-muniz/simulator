package com.sascar.simulator.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.sascar.simulator.services.MockDateService;

import org.springframework.stereotype.Service;

@Service
public class MockDateServiceImpl implements MockDateService {
    
    @Override
    public List<Date> getListOfDates(final int minutes, final int quantity) {
        final Date currentDate = new Date();
        final List<Date> list = new ArrayList<>();
        final int totalInterval = minutes * 60;
        for(int i = 0; i < quantity; i++) {
            final int interval = new Random().nextInt(totalInterval) + 1;
            final Date date = this.getDate(interval, currentDate);
            list.add(date);
        }
        return list;
    }

    @Override
    public Date getDate(final int sec, final Date date) {
        final long retryDate = Objects.isNull(date) 
            ? System.currentTimeMillis() 
            : date.getTime();
        final Timestamp original = new Timestamp(retryDate);
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        return cal.getTime();
    }
}