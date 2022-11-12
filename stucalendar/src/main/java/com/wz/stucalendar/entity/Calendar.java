package com.wz.stucalendar.entity;

public class Calendar {
    int id;
    String date;
    int hour;
    int min;
    int second;
    String notes;

    @Override
    public String toString() {
        return "Calendar{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", hour=" + hour +
                ", min=" + min +
                ", second=" + second +
                ", notes='" + notes + '\'' +
                '}';
    }



    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public Calendar() {
    }

    public Calendar(int id, String date, int hour, int min, int second, String notes) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.min = min;
        this.second = second;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
