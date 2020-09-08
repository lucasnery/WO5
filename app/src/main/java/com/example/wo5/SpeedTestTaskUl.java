package com.example.wo5;

import android.os.AsyncTask;
import android.util.Log;

import java.math.BigDecimal;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

public class SpeedTestTaskUl extends AsyncTask<Void, Void, String> {
    int count = 0;
    @Override
    protected String doInBackground(Void... params) {

        final SpeedTestSocket speedTestSocket = new SpeedTestSocket();

        // add a listener to wait for speedtest completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

            @Override
            public void onCompletion(SpeedTestReport report) {
                // called when download/upload is finished
                Log.v("speedtestUL", "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
                BigDecimal bit = report.getTransferRateBit();
                double mbit = bit.doubleValue()/1000000.0;
                count += 1;
                // called to notify download/upload progress
                if(count > 20){
                    Log.v("speedtestUL", "[PROGRESS] progress : " + percent + "%");
                    Log.v("speedtestUL", "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit() + " rate in mbis/s " + String.format("%.2f", mbit));
                }

            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                Log.v("speedtestUL","ERROR" + errorMessage);
            }
        });
        speedTestSocket.setUploadSetupTime(1);
        speedTestSocket.startFixedUpload("http://ipv4.ikoula.testdebit.info/", 100000000, 4000, 10);



        return null;
    }
}
