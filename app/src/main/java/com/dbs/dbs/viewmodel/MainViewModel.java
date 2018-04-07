package com.dbs.dbs.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.dbs.dbs.model.Appointment;

import javax.inject.Inject;


public class MainViewModel extends ViewModel {

    private LiveData<PagedList<Appointment>> mList;

    @Inject
    public MainViewModel(LiveData<PagedList<Appointment>> list) {
        mList = list;
    }

    public LiveData<PagedList<Appointment>> getList() {
        return mList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
