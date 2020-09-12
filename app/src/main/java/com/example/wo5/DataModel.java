package com.example.wo5;

import android.content.Context;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class DataModel {

    private FirebaseAuth mAuth;
    private String singleton;
    private Context context;
    private final String TAG = "DataModel";
    private FirebaseUser user;
    private Measurement measurement;
    private Measurement measurementCurrent;
    private ArrayList<Measurement> measList = new ArrayList<>();
    private ArrayList<TesteThp> testThpList = new ArrayList<>();
    private Double resultThpDl;
    private Double resultThpUl;
    private TesteThp testeThp;


    private static DataModel instance = null;

    //Constructors
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public static DataModel getInstance(){
        if(instance == null)
            instance = new DataModel();
        return instance;
    }

    //Methods
    public void thpTest() {
        TesteThp testeThp = new TesteThp(resultThpDl,resultThpUl,measurementCurrent);
        testThpList.add(testeThp);
        Log.d(TAG,testThpList.toString());

    }

    public void signOut(){
        mAuth.signOut();
    }

    public boolean addMeasList(Measurement measurement){
        measList.add(measurement);
        return true;
    }

    //Getter and setter
    public Measurement getMeasurementCurrent() {
        return measurementCurrent;
    }

    public void setMeasurementCurrent(Measurement measurementCurrent) {
        Log.d(TAG + " setMeasurementCurrent", "Ok");
        this.measurementCurrent = measurementCurrent;
    }

    public Double getResultThpDl() {
        return this.resultThpDl;
    }

    public Double getResultThpUl() {
        return this.resultThpUl;
    }

    public void setResultThpDl(Double resultThpDl) {
        this.resultThpDl = resultThpDl;
    }

    public void setResultThpUl(Double resultThpUl) {

        this.resultThpUl = resultThpUl;

    }

    public Measurement getMeasurement() {

        //measurement = measList.get(measList.size()-1);
        //Log.d(TAG,"level: " + String.valueOf(measList.get(measList.size()-1).getCellSignal().getRsrp()));
        return measurement;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }






    public String getSingleton() {
        return singleton;
    }

    public void setSingleton(String singleton) {
        this.singleton = singleton;
    }


}
