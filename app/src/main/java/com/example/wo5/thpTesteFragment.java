package com.example.wo5;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wo5.databinding.InicioLogadoBinding;
import com.example.wo5.databinding.ThpTesteFragmentBinding;

import java.util.Random;

public class thpTesteFragment extends androidx.fragment.app.Fragment implements View.OnClickListener {


    TextView textViewL1, textViewL2, textViewL3, textViewL4, textViewL5, textViewL6, textViewL7, textViewL8, textViewL9, textViewL10, textViewL11;
    TextView textViewR1, textViewR2, textViewR3, textViewR4, textViewR5, textViewR6, textViewR7, textViewR8, textViewR9, textViewR10, textViewR11;
    TextView textViewLatencia;
    TextView textViewThpDown;
    TextView textViewThpUl;
    final Handler handler = new Handler();
    private Random random = new Random();
    final private String TAG = "thpTesteFragment";
    ProgressBar progressBarLeft;
    private Runnable runnable;
    private int count = 0;

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
                int max = -40;
                int min = -125;
                int value = random.nextInt(max - min)+min;
                handler.postDelayed(this, 500);

                textViewThpDown.setText(String.valueOf(value));

                if(value > -60){
                    textViewL11.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL11.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -70){
                    textViewL10.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL10.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -80){
                    textViewL9.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL9.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -90){
                    textViewL8.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL8.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -95){
                    textViewL7.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL7.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -100){
                    textViewL6.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL6.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -105){
                    textViewL5.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL5.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -110){
                    textViewL4.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL4.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -115){
                    textViewL3.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL3.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -120){
                    textViewL2.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL2.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }
                if(value > -125){
                    textViewL1.setBackgroundColor(getResources().getColor(R.color.bluecolor,null));
                }else {
                    textViewL1.setBackgroundColor(getResources().getColor(R.color.greycolor,null));
                }

                Log.d(TAG, String.valueOf(value));
            }
        };
        handler.postDelayed(runnable, 1000);

    }



}
