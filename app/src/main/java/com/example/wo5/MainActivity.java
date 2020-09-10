package com.example.wo5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText mainEditTextSingleton;
    Button buttonSignalStrenght;
    TextView textViewEntrar;
    String singleton;
    SignalStrenght ss;
    private static final int PERMISSION_ACCESS_FINE_LOCATION = 1;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        buttonSignalStrenght = findViewById(R.id.button_prim);
        textViewEntrar = findViewById(R.id.textViewEntrar);
        //DataModel.getInstance().setContext(MainActivity.this);
        //mainEditTextSingleton = findViewById(R.id.mainEditTextSingleton);
        //DataModel.getInstance().setSingleton("Singleton funcionado");
        requestFineCoarseLocation();


        //singleton = DataModel.getInstance().getSingleton();
        //mainEditTextSingleton.setText(singleton);
        //ss = new SignalStrenght();

        buttonSignalStrenght.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.wo5.SignalStrenght.class);
                startActivity(intent);
            }
        });

        textViewEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void requestFineCoarseLocation(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_ACCESS_FINE_LOCATION);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_ACCESS_FINE_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,
                        "ACCESS FINE LOCATION OK",
                        Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,
                        "ACCESS FINE LOCATION NOK",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
