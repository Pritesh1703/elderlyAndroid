package com.dbs.dbs.viewholder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.a4direct.blanket.adapter.BaseHolder;
import com.a4direct.blanket.adapter.BaseOnClickListener;
import com.dbs.dbs.R;
import com.dbs.dbs.model.Appointment;

public class AppointmentHolder extends BaseHolder<Appointment, BaseOnClickListener<Appointment>> {

    private TextView mDate;
    private TextView mComments;

    public AppointmentHolder(View itemView) {
        super(itemView);

        mDate = (TextView) itemView.findViewById(R.id.appointment_date);
        mComments = (TextView) itemView.findViewById(R.id.appointment_commnets);
    }

    @Override
    public void bindItem(final Appointment appointment,
                         @Nullable final BaseOnClickListener<Appointment> listener) {
        mDate.setText(appointment.appointmentdate.toString());
        mComments.setText(appointment.message);

        if (listener == null) {
            return;
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener == null) {
                    return;
                }

                listener.onItemClicked(appointment);
            }
        });
    }

    @Override
    public void clear() {

    }
}
