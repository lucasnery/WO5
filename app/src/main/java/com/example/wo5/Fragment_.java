package com.example.wo5;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class Fragment_ extends androidx.fragment.app.Fragment {


    private GraphView graphFragment;
    private LineGraphSeries<DataPoint> series;
    private double graph2LastXValue = 0d;
    final Handler handler = new Handler();
    private Random random = new Random();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.textViewSwitch1);
        textView.setText("Esse é um fragment");

        graphFragment = view.findViewById(R.id.inicioLogadoFragment);
        graphFragment.getViewport().setXAxisBoundsManual(true);
        graphFragment.getViewport().setMinX(0);
        graphFragment.getViewport().setMaxX(80);
        graphFragment.getViewport().setScrollable(true);
        series = new LineGraphSeries<>();
        graphFragment.addSeries(series);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                double value = 1 + (50 - 1) * random.nextDouble();
                graph2LastXValue += 1d;
                series.appendData(new DataPoint(graph2LastXValue,value),true,200);
                handler.postDelayed(this, 500);
            }
        };

//Start
        handler.postDelayed(runnable, 1000);





    }


}
