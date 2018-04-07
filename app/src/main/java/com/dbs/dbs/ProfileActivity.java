package com.dbs.dbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dbs.dbs.model.User;


public class ProfileActivity extends AppCompatActivity {

    private TextView mAccNum;
    private TextView mEmail;
    private TextView mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAccNum = (TextView) findViewById(R.id.acc_num);
        mEmail = (TextView) findViewById(R.id.email);
        mPhoneNumber = (TextView) findViewById(R.id.phone);

        User user = Utils.getUserDetails(this);

        mAccNum.setText(user.accountid);
        mEmail.setText(user.email);
        mPhoneNumber.setText(user.phone);
    }

}
