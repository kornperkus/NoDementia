package com.kornperkus.nodementia.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarm_table")
public class Alarm {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    long time;

    int status;

    public Alarm(String title, long time, int status) {
        this.title = title;
        this.time = time;
        this.status = status;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }
}
