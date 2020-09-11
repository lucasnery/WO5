package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wo5.databinding.InicioAvancadoBinding;
import com.google.firebase.auth.FirebaseAuth;

public class InicioAvancado extends AppCompatActivity implements View.OnClickListener {

    private InicioAvancadoBinding mBinding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = InicioAvancadoBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(mBinding.getRoot());
        mBinding.vectorPlus.setOnClickListener(this);
        mBinding.vectorLevel.setOnClickListener(this);
        mBinding.vectorMap.setOnClickListener(this);
        mBinding.vectorFiles.setOnClickListener(this);
        mBinding.vectorSent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if(i == R.id.textViewSair){
            mAuth.signOut();
            Intent intent = new Intent(InicioAvancado.this,MainActivity.class);
            startActivity(intent);
        }else if(i == R.id.vectorPlus){

        }else if(i == R.id.vectorLevel){


        }else if(i == R.id.vectorMap){

        }else if(i == R.id.vectorFiles){

        }else if(i == R.id.vectorSent){

        }
    }
}
