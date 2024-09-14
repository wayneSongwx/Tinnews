package com.wayne.tinnews;


import android.os.Handler;
import android.os.Looper;

public abstract class MyAsyncTask<Request, Progress, Result> {
    private Handler handler = new Handler(Looper.getMainLooper());

    protected void onPreExecute() {

    }

    public final void execute(Request request) {
        onPreExecute();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result result = doInBackground(request);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
                    }
                });
            }
        }).start();
    }

    protected abstract Result doInBackground(Request request);


    public void publishProgress(Progress progress) {
        // move to UI thread;
        handler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(progress);
            }
        });

    }
    protected void onProgressUpdate(Progress values) {

    }


    protected void onPostExecute(Result result) {

    }

}
