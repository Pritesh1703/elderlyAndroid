package com.dbs.dbs.adapter;

import android.view.ViewGroup;

import com.a4direct.blanket.adapter.BaseOnClickListener;
import com.a4direct.blanket.adapter.GenericRecyclerAdapter;
import com.dbs.dbs.R;
import com.dbs.dbs.model.Appointment;
import com.dbs.dbs.viewholder.AppointmentHolder;


public class AppointmentAdapter extends GenericRecyclerAdapter<Appointment, BaseOnClickListener<Appointment>, AppointmentHolder> {

    @Override
    public AppointmentHolder onCreateViewHolder(ViewGroup parent, int viewId) {
        return new AppointmentHolder(inflate(R.layout.appointment_list_item, parent));
    }
}
