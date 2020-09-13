package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class Resultado extends AppCompatActivity {

    Button buttonHistorico;
    Button buttonNovoTeste;
    TextView textViewAvaliacaoResumo;
    TextView textViewAvaliacao;
    TextView textViewEntrarResultado;
    ProgressBar progressBarTesting;
    private Runnable runnableRes;
    Handler handlerRes = new Handler();
    private final String TAG = "Resultado";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        String classname = getIntent().getStringExtra("SignalStreght");
        textViewEntrarResultado = findViewById(R.id.textViewEntrarResultado);
        mAuth = FirebaseAuth.getInstance();
        createView();
        if(buttonHistorico != null){
            buttonHistorico.setVisibility(View.INVISIBLE);
        }

        textViewEntrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() == null){
                    Intent intent = new Intent(Resultado.this,Login.class);
                    intent.putExtra(TAG, this.getClass().getName());
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Resultado.this, InicioLogado.class);
                    intent.putExtra(TAG, this.getClass().getName());
                    startActivity(intent);
                }
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        createView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Resultado.this,MainActivity.class);
        startActivity(intent);
    }

    public void createView(){
        buttonHistorico = findViewById(R.id.buttonHistorico);
        buttonNovoTeste = findViewById(R.id.buttoNovoTeste);
        textViewAvaliacao = findViewById(R.id.textViewAvaliacao);
        textViewAvaliacaoResumo = findViewById(R.id.textViewAvaliacaoResumo);
        progressBarTesting = findViewById(R.id.progressBarTesting);
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
                intent.putExtra(TAG, this.getClass().getName());
                startActivity(intent);

            }
        });
        executeHandler();
        //textViewEntrarResultado = findViewById(R.id.textViewEntrarResultado);
        //textViewResultado = findViewById(R.id.textViewResultado);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //handlerRes.removeCallbacksAndMessages(runnableRes);
    }

    private void stopRunnable(){
        //handlerRes.removeCallbacksAndMessages(runnableRes);
    }

    public void executeHandler(){



        runnableRes = new Runnable() {
            @Override
            public void run() {

                Double thpDl = DataModel.getInstance().getResultThpDl();
                Double thpUl = DataModel.getInstance().getResultThpUl();


                if(thpDl != null){

                    if(thpDl > 25.0){
                        textViewAvaliacaoResumo.setText("Excelente");
                        textViewAvaliacao.setText("Sua conexão com a Internet consegue lidar com " +
                                "vários dispositivos fazendo streaming de vídeos em HD, " +
                                "videoconferência e jogos ao mesmo tempo.");
                    }
                    else if(thpDl <= 25.0 && thpDl > 10.0){
                        textViewAvaliacaoResumo.setText("Ótima");
                        textViewAvaliacao.setText("Sua conexão com a Internet consegue lidar " +
                                "com vários dispositivos fazendo streaming de vídeos em HD ao mesmo tempo.");
                    }
                    else if(thpDl <= 10.0 && thpDl > 5.0){
                        textViewAvaliacaoResumo.setText("Boa");
                        textViewAvaliacao.setText("Sua conexão com a Internet consegue lidar " +
                                "com streaming de vídeos e navegação. " +
                                "Se vários dispositivos estiverem utilizando haverá lentidão.");
                    }
                    else if(thpDl <= 5.0 && thpDl > 0.0){
                        textViewAvaliacaoResumo.setText("Ruim");
                        textViewAvaliacao.setText("Sua conexão com a Internet oferece apenas " +
                                "navegação básica.");
                    }
                    else {
                        textViewAvaliacaoResumo.setText("Sem conexão");
                        textViewAvaliacao.setText("Não foi possível se conectar a internet, verifique sua conexão.");
                    }

                    if(progressBarTesting != null){
                        progressBarTesting.setVisibility(View.INVISIBLE);
                    }

                    if(buttonHistorico != null){
                        buttonHistorico.setVisibility(View.INVISIBLE);
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
            }

        };
        handlerRes.postDelayed(runnableRes,9800);

    }
}
