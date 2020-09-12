package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Resultado extends AppCompatActivity {

    Button buttonHistorico;
    Button buttonNovoTeste;
    TextView textViewAvaliacaoResumo;
    TextView textViewAvaliacao;
    private Runnable runnableRes;
    Handler handlerRes = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        buttonHistorico = findViewById(R.id.buttonHistorico);
        buttonNovoTeste = findViewById(R.id.buttoNovoTeste);
        textViewAvaliacao = findViewById(R.id.textViewAvaliacao);
        textViewAvaliacaoResumo = findViewById(R.id.textViewAvaliacaoResumo);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.result_fragment, new thpTesteFragment());
        ft.commit();
        if(buttonHistorico != null){
            buttonHistorico.setVisibility(View.INVISIBLE);
        }
        if(buttonNovoTeste != null){
            buttonNovoTeste.setVisibility(View.INVISIBLE);
        }
        if(textViewAvaliacaoResumo != null){
            textViewAvaliacaoResumo.setVisibility(View.INVISIBLE);
        }
        if(textViewAvaliacao != null){
            textViewAvaliacao.setVisibility(View.INVISIBLE);
        }
        buttonNovoTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultado.this,SignalStrenght.class);
                startActivity(intent);
            }
        });
        executeHandler();
        //textViewEntrarResultado = findViewById(R.id.textViewEntrarResultado);
        //textViewResultado = findViewById(R.id.textViewResultado);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Resultado.this,MainActivity.class);
        startActivity(intent);
    }


    public void executeHandler(){

        runnableRes = new Runnable() {
            @Override
            public void run() {

                if(buttonHistorico != null){
                    buttonHistorico.setVisibility(View.VISIBLE);
                }
                if(buttonNovoTeste != null){
                    buttonNovoTeste.setVisibility(View.VISIBLE);
                }
                if(textViewAvaliacaoResumo != null){
                    textViewAvaliacaoResumo.setVisibility(View.VISIBLE);
                }
                if(textViewAvaliacao != null){
                    textViewAvaliacao.setVisibility(View.VISIBLE);
                }

            }
        };
        handlerRes.postDelayed(runnableRes,9800);
    }
}
