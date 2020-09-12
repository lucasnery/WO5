package com.example.wo5;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class thpTesteFragment extends Fragment implements View.OnClickListener {


    TextView textViewL1, textViewL2, textViewL3, textViewL4, textViewL5, textViewL6, textViewL7, textViewL8, textViewL9, textViewL10, textViewL11;
    TextView textViewR1, textViewR2, textViewR3, textViewR4, textViewR5, textViewR6, textViewR7, textViewR8, textViewR9, textViewR10, textViewR11;
    TextView textViewLatencia;
    TextView textViewThpDown;
    TextView textViewThpUl;
    TextView textViewQual;
    TextView textViewIntens;
    TextView textViewCenterV;
    final Handler handler = new Handler();
    private Random random = new Random();
    final private String TAG = "thpTesteFragment";
    private Runnable runnable;
    private int count = 0;
    Measurement meas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thp_teste_fragment,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewL1 = view.findViewById(R.id.textViewL1);
        textViewL2 = view.findViewById(R.id.textViewL2);
        textViewL3 = view.findViewById(R.id.textViewL3);
        textViewL4 = view.findViewById(R.id.textViewL4);
        textViewL5 = view.findViewById(R.id.textViewL5);
        textViewL6 = view.findViewById(R.id.textViewL6);
        textViewL7 = view.findViewById(R.id.textViewL7);
        textViewL8 = view.findViewById(R.id.textViewL8);
        textViewL9 = view.findViewById(R.id.textViewL9);
        textViewL10 = view.findViewById(R.id.textViewL10);
        textViewL11 = view.findViewById(R.id.textViewL11);
        textViewR1 = view.findViewById(R.id.textViewR1);
        textViewR2 = view.findViewById(R.id.textViewR2);
        textViewR3 = view.findViewById(R.id.textViewR3);
        textViewR4 = view.findViewById(R.id.textViewR4);
        textViewR5 = view.findViewById(R.id.textViewR5);
        textViewR6 = view.findViewById(R.id.textViewR6);
        textViewR7 = view.findViewById(R.id.textViewR7);
        textViewR8 = view.findViewById(R.id.textViewR8);
        textViewR9 = view.findViewById(R.id.textViewR9);
        textViewR10 = view.findViewById(R.id.textViewR10);
        textViewR11 = view.findViewById(R.id.textViewR11);
        textViewLatencia = view.findViewById(R.id.textViewLatencia);
        textViewThpDown = view.findViewById(R.id.textViewThpDown);
        textViewThpUl = view.findViewById(R.id.textViewThpUp);
        textViewQual = view.findViewById(R.id.textViewQual);
        textViewIntens = view.findViewById(R.id.textViewIntens);
        textViewCenterV = view.findViewById(R.id.textViewCenterV);


        executeHandler();



    }

    @Override
    public void onResume() {
        super.onResume();
        handler.removeCallbacks(runnable);
        executeHandler();



    }



    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(runnable);
        handler.postDelayed(runnable, 10000);
    }


    @Override
    public void onClick(View v) {


    }

    public void executeHandler(){


        runnable = new Runnable() {
            @Override
            public void run() {
                meas = DataModel.getInstance().getMeasurement();
                int rsrp = meas.getCellSignal().getRsrp();
                int rsrq = meas.getCellSignal().getRsrq();
                //int max = -40;
                //int min = -125;
                //int value = random.nextInt(max - min)+min;
                handler.postDelayed(this, 500);
                textViewIntens.setText(String.valueOf(rsrp));
                textViewQual.setText(String.valueOf(rsrq));
                Double thpDlResult = DataModel.getInstance().getResultThpDl();
                Double thpUlResult = DataModel.getInstance().getResultThpUl();
                if(thpDlResult == null){
                    textViewThpDown.setText("?");
                }
                else {
                    textViewThpDown.setText(String.format("%.2f",thpDlResult));
                }
                if(thpUlResult == null){
                    textViewThpUl.setText("?");

                }
                else {
                    textViewThpUl.setText(String.format("%.2f", thpUlResult));
                }


                //textViewThpDown.setText(String.valueOf(value));

                //RSRQ
                if(rsrq > -6){
                    textViewL11.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL11.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -8){
                    textViewL10.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL10.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -10){
                    textViewL9.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL9.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -12){
                    textViewL8.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL8.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -14){
                    textViewL7.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL7.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -16){
                    textViewL6.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL6.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -18){
                    textViewL5.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL5.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -20){
                    textViewL4.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL4.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -22){
                    textViewL3.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL3.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -24){
                    textViewL2.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL2.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrq > -26){
                    textViewL1.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL1.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }

                //RSRP
                if(rsrp > -80){
                    textViewR11.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR11.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -90){
                    textViewR10.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR10.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -100){
                    textViewR9.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR9.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -102){
                    textViewR8.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR8.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -105){
                    textViewR7.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR7.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -108){
                    textViewR6.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR6.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -110){
                    textViewR5.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR5.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -112){
                    textViewR4.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR4.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -114){
                    textViewR3.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR3.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -116){
                    textViewR2.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR2.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(rsrp > -120){
                    textViewR1.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewR1.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }

                Log.d(TAG, "RSRP" + String.valueOf(rsrp) + " RSRQ " + String.valueOf(rsrq));
            }
        };
        handler.postDelayed(runnable, 1000);

    }



}
