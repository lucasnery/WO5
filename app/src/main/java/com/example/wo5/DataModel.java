package com.example.wo5;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DataModel {

    private FirebaseUser user;
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

    public String getSingleton() {
        return singleton;
    }

    public void setSingleton(String singleton) {
        this.singleton = singleton;
    }

}
