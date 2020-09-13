package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.wo5.databinding.InicioAvancadoBinding;
import com.google.firebase.auth.FirebaseAuth;

public class InicioAvancado extends AppCompatActivity implements View.OnClickListener {

    private InicioAvancadoBinding mBinding;
    private FirebaseAuth mAuth;
    private final String TAG = "InicioAvancado";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = InicioAvancadoBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(mBinding.getRoot());
        mBinding.vectorPlus2.setOnClickListener(this);
        mBinding.vectorLevel.setOnClickListener(this);
        mBinding.vectorMap.setOnClickListener(this);
        mBinding.vectorFiles.setOnClickListener(this);
        mBinding.vectorSent.setOnClickListener(this);

        String signalStrength = getIntent().getStringExtra("SignalStrenght");
        Log.d(TAG,"Extra " + signalStrength);
        if(signalStrength != null){
            Log.d(TAG,"Extra " + signalStrength + " Ok");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.avancadoFragment, new thpTesteFragment());
            ft.commit();
        }
//
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.avancadoFragment, new LogoFragment());
//        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(InicioAvancado.this,InicioLogado.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if(i == R.id.textViewSair2){
            mAuth.signOut();
            Intent intent = new Intent(InicioAvancado.this,MainActivity.class);
            intent.putExtra(TAG,TAG);
            startActivity(intent);
        }else if(i == R.id.vector_plus2){
            Intent intent = new Intent(InicioAvancado.this,SignalStrenght.class);
            intent.putExtra(TAG, TAG);
            startActivity(intent);

        }else if(i == R.id.vectorLevel){


        }else if(i == R.id.vectorMap){

        }else if(i == R.id.vectorFiles){

        }else if(i == R.id.vectorSent){

        }
    }
}
