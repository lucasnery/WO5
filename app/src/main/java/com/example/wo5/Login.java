package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    TextView n_o_tem_cad;
    Button buttonEntrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        n_o_tem_cad = findViewById(R.id.n_o_tem_cad);
        buttonEntrar = findViewById(R.id.buttonEntrar);


        n_o_tem_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Cadastrar.class);
                startActivity(intent);
            }
        });

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,com.example.wo5.Inicio_Logado.class);
                startActivity(intent);
            }
        });

    }
}
