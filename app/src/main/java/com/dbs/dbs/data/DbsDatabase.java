package com.dbs.dbs.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dbs.dbs.model.Appointment;


@Database(entities = {Appointment.class}, version = 1, exportSchema = false)
public abstract class DbsDatabase extends RoomDatabase {

    public abstract AppointmentDao appointmentDao();

}
