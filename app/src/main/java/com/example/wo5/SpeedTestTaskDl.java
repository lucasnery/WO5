package com.example.wo5;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

public class SpeedTestTaskDl extends AsyncTask<Void, Void, String>{

    private long startTime;
    int count = 0;
    @Override
    protected String doInBackground(Void... params) {

        final SpeedTestSocket speedTestSocket = new SpeedTestSocket();
        speedTestSocket.setSocketTimeout(4500);


        // add a listener to wait for speedtest completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

            @Override
            public void onCompletion(SpeedTestReport report) {
                // called when download/upload is finished
                long difference = System.currentTimeMillis() - startTime;
                BigDecimal bit = report.getTransferRateBit();
                double mbit = bit.doubleValue()/1000000.0;
                Log.v("speedtestDL", "[COMPLETED] time   : " + difference);
                Log.v("speedtestDL", "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
                Log.v("speedtestDL", "[COMPLETED] rate in Mbit/s   : " + String.format("%.2f", mbit));
                DataModel.getInstance().setResultThpDl(mbit);
                new SpeedTestTaskUl().execute();
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
                // called to notify download/upload progress
                BigDecimal bit = report.getTransferRateBit();
                double mbit = bit.doubleValue()/1000000.0;
                long difference = System.currentTimeMillis() - startTime;
                count += 1;
                if(count > 20){
                    Log.v("speedtestDL", "[PROGRESS] progress : " + percent + "%");
                    Log.v("speedtestDL", "[PROGRESS] report time : " + report.getReportTime());
                    Log.v("speedtestDL", "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit() + " rate in mbis/s " + String.format("%.2f", mbit));
                    //DataModel.getInstance().addThpDl(mbit);
                }

                if(difference > speedTestSocket.getSocketTimeout()){
                    speedTestSocket.forceStopTask();
                }
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                Log.v("speedtestDL","ERROR" + errorMessage);
            }
        });

        speedTestSocket.startDownload("http://ipv4.ikoula.testdebit.info/100M.iso",1000);
        this.startTime = System.currentTimeMillis();

        return null;
    }
}
