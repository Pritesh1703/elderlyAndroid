package com.dbs.dbs;

import com.dbs.dbs.model.Appointment;
import com.dbs.dbs.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {

    // Gets list of environments
    @GET("appointment/{id}")
    Call<List<Appointment>> getAppointments(@Path("id") String id);

    // Uploads a wav file with properties
    @POST("appointment")
    Call<ResponseBody> newAppointment(@Body Appointment app);

    // Uploads a wav file with properties
    @GET("user/{accountid}")
    Call<User> getUser(@Path("accountid") String id);

}
