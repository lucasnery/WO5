package com.example.wo5;

import android.content.Context;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class DataModel {

    private final String TAG = "DataModel";
    private FirebaseUser user;
    private Measurement measurement;
    private ArrayList<Measurement> measList = new ArrayList<>();
    private Double resultThpDl;
    private Double resultThpUl;

    public Double getResultThpDl() {
        return resultThpDl;
    }

    public Double getResultThpUl() {
        return resultThpUl;
    }

    public void setResultThpDl(Double resultThpDl) {
        this.resultThpDl = resultThpDl;
    }

    public void setResultThpUl(Double resultThpUl) {
        this.resultThpUl = resultThpUl;
    }

    public Measurement getMeasurement() {

        measurement = measList.get(measList.size()-1);
        Log.d(TAG,"level: " + String.valueOf(measList.get(measList.size()-1).getCellSignal().getRsrp()));
        return measurement;
    }


    public boolean addMeasList(Measurement measurement){
        measList.add(measurement);
        return true;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    private FirebaseAuth mAuth;
    private String singleton;
    private Context context;

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }



    private static DataModel instance = null;



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

    public void signOut(){
        mAuth.signOut();
    }

    public String getSingleton() {
        return singleton;
    }

    public void setSingleton(String singleton) {
        this.singleton = singleton;
    }

}
