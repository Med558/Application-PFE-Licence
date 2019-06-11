package com.theangel.ericsson.ericsson.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.theangel.ericsson.ericsson.async.GetNotification;

public class SyncService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GetNotification async = new GetNotification(getApplicationContext());
        async.execute();

        return super.onStartCommand(intent, flags, startId);
    }
}
