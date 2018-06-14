package com.example.bpeters.simpletodo.utilities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadManager {
    private static final int CORE_POOL_SIZE = 4;
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void runOnBackgroundThread(final Runnable runnable) {
        if (runnable == null) {
            return;
        }

        SCHEDULED_EXECUTOR_SERVICE.execute(runnable);
    }

    @SuppressLint("NewApi")
    public static void runOnUiThread(final Runnable runnable) {
        final Looper looper = Looper.getMainLooper();

        if (looper.isCurrentThread()){
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }
}
