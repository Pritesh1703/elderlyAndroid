package com.dbs.dbs.utils;


import android.os.Looper;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private final DiskIOThreadExecutor diskIO;

    private final Executor networkIO;

    private final MainThreadExecutor mainThread;

    @VisibleForTesting
    public AppExecutors(DiskIOThreadExecutor diskIO, Executor networkIO, MainThreadExecutor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public AppExecutors() {
        this(new DiskIOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }

    public DiskIOThreadExecutor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public MainThreadExecutor mainThread() {
        return mainThread;
    }

    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
