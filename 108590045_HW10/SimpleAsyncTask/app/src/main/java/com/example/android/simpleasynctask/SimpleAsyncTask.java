/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.simpleasynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * The SimpleAsyncTask class extends AsyncTask to perform a very simple
 * background task -- in this case, it just sleeps for a random amount of time.
 */
public class SimpleAsyncTask extends AsyncTask<Integer,Integer,String>{
    private WeakReference<MainActivity> activityWeakReference;
    SimpleAsyncTask(MainActivity activity){
        activityWeakReference = new WeakReference<MainActivity>(activity);
    }
    @Override
    protected String doInBackground(Integer... integers) {
        for(int i=0;i<integers[0];i++){
            publishProgress((i*100)/integers[0]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Awake at last after sleeping for " + 200 + " milliseconds!";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity activity = activityWeakReference.get();
        if(activity == null || activity.isFinishing()){
            return;
        }
        activity.mpb.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity activity = activityWeakReference.get();
        if(activity == null || activity.isFinishing()){
            return;
        }
        activity.mpb.setProgress(0);
        activity.mpb.setVisibility(View.INVISIBLE);
        activity.mTextView.setText(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        MainActivity activity = activityWeakReference.get();
        if(activity == null || activity.isFinishing()){
            return;
        }
        activity.mpb.setProgress(values[0]);

    }
}

