package com.kornperkus.nodementia.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDao {

    @Insert
    void insert(Alarm alarm);

    @Query("SELECT * FROM alarm_table")
    LiveData<List<Alarm>> getAllAlarms();

    @Update
    void update(Alarm alarm);

    @Delete
    void delete(Alarm alarm);
}
