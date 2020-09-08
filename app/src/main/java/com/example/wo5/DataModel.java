package com.example.wo5;

import android.content.Context;

public class DataModel {

    private String singleton;
    private Context context;

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
