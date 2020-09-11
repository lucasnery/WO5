package com.example.wo5;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.resultado_fragment, new thpTesteFragment());
        ft.commit();
    }


}
