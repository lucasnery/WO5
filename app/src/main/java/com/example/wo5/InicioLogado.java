package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.wo5.databinding.InicioLogadoBinding;
import com.google.firebase.auth.FirebaseAuth;

public class InicioLogado extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private InicioLogadoBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = InicioLogadoBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(mBinding.getRoot());
        mBinding.textViewSair.setOnClickListener(this);
        mBinding.vectorUser.setOnClickListener(this);
        mBinding.vectorAntennaF.setOnClickListener(this);
        mBinding.vectorDashboard.setOnClickListener(this);
        mBinding.vectorAdmin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.logadoFragment, new LogoFragment());
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(InicioLogado.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.textViewSair){
            mAuth.signOut();
            Intent intent = new Intent(InicioLogado.this,MainActivity.class);
            startActivity(intent);
        }else if(i == R.id.vector_user){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.logadoFragment, new PerfilFragment());
            ft.commit();

        }else if(i == R.id.vector_antennaF){
            Intent intent = new Intent(InicioLogado.this,InicioAvancado.class);
            startActivity(intent);

        }else if(i == R.id.vectorDashboard){

        }else if(i == R.id.vectorAdmin){

        }else if(i == R.id.vector_user){

        }
    }
}
