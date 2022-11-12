package com.wz.stucalendar.dao;

import com.wz.stucalendar.entity.Calendar;

import java.util.List;

public interface CalendarDao {
        void save(Calendar calendar);

        void update(Calendar calendar);

        Calendar findByDate(String date);

        void delete(Calendar calendar);

        void resave(Calendar calendar);

        String ensure(String date1);

        List<Calendar> lists();
}
