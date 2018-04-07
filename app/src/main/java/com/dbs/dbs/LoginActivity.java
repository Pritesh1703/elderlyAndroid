package com.dbs.dbs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dbs.dbs.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_ALL_PERMISSIONS = 112;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditText = (EditText) findViewById(R.id.account_id);
        mEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        ArrayList<String> allPerms = getAllPermissions();
        if (checkPermissions(this, REQUEST_ALL_PERMISSIONS, allPerms)) {
            initializeViews();
        }
    }

    private void initializeViews() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = mEditText.getText().toString();
                if (text == null || text.isEmpty()) {
                    return;
                }

                ApiService api = RetroClient.getApiService();
                Call<User> call = api.getUser(text);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            Utils.setUserToPreferences(LoginActivity.this, user);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("acc", text);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // TODO : Show toast
                        Log.i("Jyothi", "Jyothi");
                    }
                });
            }
        });
    }

    public static boolean checkPermissions(Context ctx, int returnCode, ArrayList<String> perms) {
        if (perms.size() == 0) {
            return true;
        }

        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        ArrayList<String> list = new ArrayList<String>();

        for (String in : perms) {
            checkPermission(ctx, in, list);
        }

        if (!list.isEmpty()) {
            ActivityCompat.requestPermissions(((Activity) ctx),
                    list.toArray(new String[list.size()]),
                    returnCode);
            return false;
        } else {
            return true;
        }
    }

    private static void checkPermission(Context ctx, String perm, ArrayList<String> list) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ctx, perm)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            list.add(perm);
        }
    }

    /**
     * Creating a list of permissions needed for the application, to seek user
     * permission.
     *
     * @return list
     */
    public ArrayList<String> getAllPermissions() {
        ArrayList<String> allPermissions = new ArrayList<String>();
        allPermissions.add(Manifest.permission.INTERNET);
        allPermissions.add(Manifest.permission.ACCESS_WIFI_STATE);
        allPermissions.add(Manifest.permission.CHANGE_WIFI_STATE);
        allPermissions.add(Manifest.permission.ACCESS_NETWORK_STATE);
        allPermissions.add(Manifest.permission.READ_CALENDAR);
        allPermissions.add(Manifest.permission.WRITE_CALENDAR);

        return allPermissions;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ALL_PERMISSIONS: {
                boolean grant = true;
                for (int ret : grantResults) {
                    if (ret != PackageManager.PERMISSION_GRANTED) {
                        grant = false;
                    }
                }

                if (grant) {
                    initializeViews();
                } else {
                    finish();
                }

                break;
            }
        }
    }
}
