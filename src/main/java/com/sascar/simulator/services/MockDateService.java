package com.sascar.simulator.services;

import java.util.Date;
import java.util.List;

public interface MockDateService {
    Date getDate(final int sec, final Date date);
    List<Date> getListOfDates(final int minutes, final int quantity);
}