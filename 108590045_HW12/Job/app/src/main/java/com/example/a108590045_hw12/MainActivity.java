package com.example.a108590045_hw12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 0;
    private static final long ONE_DAY_INTERVAL = 24 * 60 * 60 * 1000L;
    private JobScheduler mScheduler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setJob(View view) {
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        builder.setRequiresDeviceIdle(true);
        builder.setRequiresCharging(true);
        builder.setPeriodic(ONE_DAY_INTERVAL);
        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);
        Toast.makeText(this, "Job Scheduled", Toast.LENGTH_SHORT).show();
    }

    public void downloadJob(View view) {
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);
    }

    public void cancelJob(View view) {
        if (mScheduler!=null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
