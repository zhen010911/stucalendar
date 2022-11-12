package com.wz.stucalendar.service;

import com.wz.stucalendar.dao.CalendarDao;
import com.wz.stucalendar.entity.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService{
    private CalendarDao calendarDao;
    @Autowired
    public NoteServiceImpl(CalendarDao calendarDao) {
        this.calendarDao = calendarDao;
    }

    @Override
    public List<Calendar> lists() {
        return calendarDao.lists();
    }

    @Override
    public void save(Calendar calendar) {
        Calendar calendarDB = calendarDao.findByDate(calendar.getDate());
        if (!ObjectUtils.isEmpty(calendarDB)) throw new RuntimeException("当天日期备忘录已存在，请重新选择!");
        calendarDao.save(calendar);
    }


    @Override
    public void update(Calendar calendar) {
        calendarDao.update(calendar);
    }

    @Override
    public void delete(Calendar calendar) {
        calendarDao.delete(calendar);
    }

    @Override
    public boolean EnsureByName(String date1) {
        boolean sure;
        System.out.println("calendarDao.ensure(date1)= "+calendarDao.ensure(date1));
        if (calendarDao.ensure(date1)!=null)
            sure=true;
        else sure =  false;
        return sure;
    }

}
