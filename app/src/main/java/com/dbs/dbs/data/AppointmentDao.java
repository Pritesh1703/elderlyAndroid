package com.dbs.dbs.data;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dbs.dbs.model.Appointment;


@Dao
public interface AppointmentDao {

    @Query("SELECT * FROM appointment")
    public abstract DataSource.Factory<Integer, Appointment> getAppointmentList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAppointment(Appointment task);

}
