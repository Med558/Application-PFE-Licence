package com.theangel.ericsson.ericsson.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Intent service = new Intent(getApplicationContext(), SyncService.class);
        getApplicationContext().startService(service);
        Utils.scheduleJob(getApplicationContext());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
