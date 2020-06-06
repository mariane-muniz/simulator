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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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
    public Date getDate(final int sec, Date date) {

        if (Objects.nonNull(date)) {
            long difference = this.differenceSeconds(date);
            if (difference > 10) {
                final List<Date> dateList = this.getListOfDates(2, 1);
                if (!CollectionUtils.isEmpty(dateList))
                    date = dateList.get(0);
            }
        }

        final long retryDate = Objects.isNull(date)
                ? System.currentTimeMillis()
                : date.getTime();

        final Timestamp original = new Timestamp(retryDate);
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        return cal.getTime();
    }

    private long differenceSeconds(final Date date) {
        Assert.notNull(date, "date cannot be null.");
        return ((new Date().getTime()-date.getTime()) / 1000);
    }
}