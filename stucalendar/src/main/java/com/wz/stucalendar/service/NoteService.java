package com.wz.stucalendar.service;

import com.wz.stucalendar.entity.Calendar;

import java.util.List;

public interface NoteService {
    void save(Calendar calendar);

    void update(Calendar calendar);

    void delete(Calendar calendar);

    boolean EnsureByName(String date1);

    List<Calendar> lists();
}
