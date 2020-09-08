package com.example.wo5;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Inicio_Logado extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_logado);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.graphFragment, new Fragment_());
        ft.commit();
    }
}
