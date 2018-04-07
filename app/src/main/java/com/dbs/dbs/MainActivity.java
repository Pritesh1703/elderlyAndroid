package com.dbs.dbs;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.a4direct.blanket.adapter.BaseOnClickListener;
import com.dbs.dbs.adapter.AppointmentAdapter;
import com.dbs.dbs.model.Appointment;
import com.dbs.dbs.model.User;
import com.dbs.dbs.viewmodel.MainViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BaseOnClickListener<Appointment> {

    private AppointmentAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MainViewModel mViewModel;

    private String mAccountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mAccountId = intent.getStringExtra("acc");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewAppointmentActivity.class);
                startActivity(intent);
            }
        });

        setupAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAppointments();
    }

    private void setupAdapter() {
        mAdapter = new AppointmentAdapter();
        mAdapter.setListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);

        getAppointments();
    }

    private void getAppointments() {
        ApiService api = RetroClient.getApiService();
        Call<List<Appointment>> call = api.getAppointments(mAccountId);
        call.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                if (response.isSuccessful()) {
                    mAdapter.setItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {

            }
        });
    }

    private void initializeDrivers() {
        mViewModel.getList().observe(this, new Observer<PagedList<Appointment>>() {
            @Override
            public void onChanged(@Nullable PagedList<Appointment> appointments) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(Appointment appointment) {
        Toast.makeText(this, appointment.message, Toast.LENGTH_SHORT).show();

    }


}
