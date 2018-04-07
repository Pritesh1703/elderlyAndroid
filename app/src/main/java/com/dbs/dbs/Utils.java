package com.dbs.dbs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dbs.dbs.data.AppointmentDao;
import com.dbs.dbs.model.Appointment;
import com.dbs.dbs.model.AppointmentResponse;
import com.dbs.dbs.model.User;
import com.google.gson.Gson;

import java.util.List;

public class Utils {

    private static final String USER = "user";

    public static List<Appointment> getList() {
        String str = "{\n" +
                "        \"posts\": [\n" +
                "        { \"appId\": 1, \"custId\": \"1211245675\", \"comments\" : \"Want to send money\", date: \"2013-09-23T18:25:43.511Z\"},\n" +
                "        { \"appId\": 1, \"custId\": \"1256693466\", \"comments\" : \"I want to know details\", date: \"2015-02-23T18:25:43.511Z\"},\n" +
                "        { \"appId\": 1, \"custId\": \"547467844\", \"comments\" : \"Want to know few details\", date: \"2012-04-23T18:25:43.511Z\"},\n" +
                "        { \"appId\": 1, \"custId\": \"86550844\", \"comments\" : \"Money transfer\", date: \"2018-09-23T18:25:43.511Z\"},\n" +
                "        { \"appId\": 1, \"custId\": \"86456745\", \"comments\" : \"Details\", date: \"2016-09-23T18:25:43.511Z\"},\n" +
                "        { \"appId\": 1, \"custId\": \"27536875\", \"comments\" : \"Want appointment\", date: \"2015-07-23T18:25:43.511Z\"}]}";

        Gson gson = new Gson();
        AppointmentResponse response = gson.fromJson(str, AppointmentResponse.class);
        return response.posts;
    }

    public static void setUserToPreferences(Context context, User userInfo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("phone", userInfo.phone);
        editor.putString("accountid", userInfo.accountid);
        editor.putString("age", userInfo.age);
        editor.putString("email", userInfo.email);
        editor.putString("longitude", userInfo.longitude);
        editor.putString("latitude", userInfo.latitude == null ? "" : userInfo.latitude);

        editor.apply();
    }

    public static User getUserDetails(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER,
                Context.MODE_PRIVATE);

        User user = new User();
        user.latitude = sharedPreferences.getString("latitude", "");
        user.longitude = sharedPreferences.getString("longitude", "");
        user.age = sharedPreferences.getString("age", "");
        user.accountid = sharedPreferences.getString("accountid", "");
        user.phone = sharedPreferences.getString("phone", "");
        user.email = sharedPreferences.getString("email", "");

        return user;
    }
}
