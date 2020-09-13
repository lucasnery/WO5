package com.example.wo5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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
    Button button_prim;
    String singleton;
    SignalStrenght ss;
    private static final int PERMISSION_ACCESS_FINE_LOCATION = 1;

    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        buttonSignalStrenght = findViewById(R.id.button_prim);
        textViewEntrar = findViewById(R.id.textViewEntrar);
        button_prim = findViewById(R.id.button_prim);
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
                intent.putExtra(TAG, this.getClass().getName());
                startActivity(intent);
            }
        });

        textViewEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() == null){
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    intent.putExtra(TAG, this.getClass().getName());
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, InicioLogado.class);
                    intent.putExtra(TAG, this.getClass().getName());
                    startActivity(intent);
                }
            }
        });

        button_prim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SignalStrenght.class);
                intent.putExtra(TAG,TAG);
                startActivity(intent);
                //Intent intent1 = new Intent(MainActivity.this,Resultado.class);
                //startActivity(intent1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //updateUI(mAuth.getCurrentUser());
    }


    @Override
    protected void onResume() {
        super.onResume();
        //updateUI(mAuth.getCurrentUser());
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

    private void updateUI(FirebaseUser user) {


        if (user != null) {
            //textViewEntrar.setText("Sair");
            Log.d(TAG, mAuth.getCurrentUser().getEmail());
            Toast.makeText(MainActivity.this,"Usuário Logado",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, InicioLogado.class);
            startActivity(intent);

            //mBinding.status.setText(getString(R.string.emailpassword_status_fmt,
            //        user.getEmail(), user.isEmailVerified()));
            //mBinding.detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            //mBinding.emailPasswordButtons.setVisibility(View.GONE);
            //mBinding.editTextPassword.setVisibility(View.GONE);
            //mBinding.signedInButtons.setVisibility(View.VISIBLE);

            if (user.isEmailVerified()) {
                //mBinding.verifyEmailButton.setVisibility(View.GONE);
            } else {
                //mBinding.verifyEmailButton.setVisibility(View.VISIBLE);
            }
        } else {

            //mBinding.status.setText(R.string.signed_out);
            // mBinding.detail.setText(null);

            //mBinding.emailPasswordButtons.setVisibility(View.VISIBLE);
            //mBinding.emailPasswordFields.setVisibility(View.VISIBLE);
            // mBinding.signedInButtons.setVisibility(View.GONE);
        }
    }


}
