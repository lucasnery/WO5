package com.example.wo5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    FirebaseDatabase database = FirebaseDatabase.getInstance();


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
        Log.d(TAG, "Latitude" + measurementCurrent.getLocation().getLatitude());
        testThpList.add(testeThp);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            saveFirebase();
        }
        Log.d(TAG,testThpList.toString());

    }

    public void saveLog(String text){
        @SuppressLint("SdCardPath") File logFile = new File("/data/data/com.example.wo5/Log/log.txt");
        if(!logFile.exists()){
            try {
                logFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void saveFirebase(){
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getUid() != null) {

            DatabaseReference myRef = database.getReference(mAuth.getUid());
            myRef.child("ThpTest");
            String children = measurementCurrent.getDateTime().getDate();
            String [] childArr = children.split("/");
            String child = childArr[2] + childArr[1] + childArr[0] + " " +  measurementCurrent.getDateTime().getTime();;
            Log.d("Child",children);
            myRef.child("ThpTest").child(child).child("Datetime").setValue(measurementCurrent.getDateTime());
            myRef.child("ThpTest").child(child).child("CellIdentity").setValue(measurementCurrent.getCellIdentity());
            myRef.child("ThpTest").child(child).child("CellSignal").setValue(measurementCurrent.getCellSignal());
            myRef.child("ThpTest").child(child).child("Location").setValue(measurementCurrent.getLocation());
            myRef.child("ThpTest").child(child).child("ThpDl").setValue(resultThpDl);
            myRef.child("ThpTest").child(child).child("ThpUl").setValue(resultThpUl);
            getTestChange();
            Log.d("Firebase data myRef", myRef.toString());
            getTestChange();
        }
    }

    public void getAllTest(){
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getUid() != null){
            DatabaseReference myRef = database.getReference(mAuth.getUid());
            myRef.child("ThpTest").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot s: snapshot.getChildren()){
                        String thpDl = String.valueOf(s.child("ThpDl").getValue());
                        String thpUl = String.valueOf(s.child("ThpUl").getValue());
                        String date  = String.valueOf(s.child("Datetime").child("date").getValue());
                        String time  = String.valueOf(s.child("Datetime").child("time").getValue());
                        Log.d(TAG,"thpDl " + thpDl + " thpUL: " + thpUl + " date " + date + " time " + time);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    public void getTestChange(){

        if(mAuth.getUid() != null){
            DatabaseReference myRef = database.getReference(mAuth.getUid());
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAG,"onChildAdded " + snapshot.toString());
                    for(DataSnapshot s: snapshot.getChildren()){
                        String thpDl = String.valueOf(s.child("ThpDl").getValue());
                        String thpUl = String.valueOf(s.child("ThpUl").getValue());
                        String date  = String.valueOf(s.child("Datetime").child("date").getValue());
                        String time  = String.valueOf(s.child("Datetime").child("time").getValue());
                        Log.d(TAG,"thpDl " + thpDl + " thpUL: " + thpUl + " date " + date + " time " + time);
                    }
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAG,"onChildChanged " + snapshot.toString());
                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Log.d(TAG,"onChildRemoved " + snapshot.toString());
                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d(TAG,"onChildRemoded hist " + snapshot.toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d(TAG,"onChildCancelled " + error.toString());
                }
            });
        }
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
