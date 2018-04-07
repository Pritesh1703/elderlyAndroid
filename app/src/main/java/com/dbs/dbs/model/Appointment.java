package com.dbs.dbs.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a4direct.blanket.adapter.BaseObject;
import com.dbs.dbs.utils.DateTypeConverter;

import java.util.Date;

@Entity(tableName = "appointment")
public class Appointment extends BaseObject {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String id;

    @Nullable
    @ColumnInfo(name = "accountid")
    public String accountid;

    @Nullable
    @ColumnInfo(name = "message")
    public String message;

    @Nullable
    @ColumnInfo(name = "appointmentdate")
    public String appointmentdate;

    @Override
    public String getId() {
        return id;
    }
}
