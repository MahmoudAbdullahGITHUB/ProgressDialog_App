package com.example.beso.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Activity myActivity;
    ProgressDialog dialog;
    myFirstThread myFirstThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myActivity = this;

    }


    public void BuRun(View view) {

        dialog = new ProgressDialog(this);

        dialog.setTitle("Downloading dialog ....");
        dialog.setMessage("Download is progress ...");
        dialog.setProgressStyle(dialog.STYLE_HORIZONTAL);
        dialog.setProgress(0);
        dialog.setMax(20);
        dialog.show();

        myFirstThread = new myFirstThread();
        myFirstThread.start();

    }

    class myFirstThread extends Thread{
        @Override
        public void run() {
            while (dialog.getProgress()<20){
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.incrementProgressBy(1);
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch (Exception ex){

                }
            }

            myActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            });

        }
    }

}
