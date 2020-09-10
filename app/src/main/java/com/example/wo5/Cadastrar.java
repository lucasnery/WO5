package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Cadastrar extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private final String TAG = "Cadastrar";
    private Button buttonCadastrar;
    private EditText editTextCadastrarNome;
    private EditText editTextCadastrarEmail;
    private EditText editTextCadastrarSenha;
    private EditText editTextCadastrarConfSenha;
    private ProgressBar progressBarCadastrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);
        editTextCadastrarNome = findViewById(R.id.editTextCadastrarNome);
        editTextCadastrarEmail = findViewById(R.id.editTextCadastrarEmail);
        editTextCadastrarSenha = findViewById(R.id.editTextCadastrarSenha);
        editTextCadastrarConfSenha = findViewById(R.id.editTextCadastrarConfSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        progressBarCadastrar = findViewById(R.id.progressBarCadastrar);
        mAuth = FirebaseAuth.getInstance();
        buttonCadastrar.setOnClickListener(this);

    }

    private void createAccount(String email, String password){
        Log.d(TAG, "createAccount:" + email);
        if(!validateForm()){
            return;
        }
        showProgressBar();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Cadastrar.this, "Usuário criado com sucesso.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Log.d(TAG, String.valueOf(user));
                            DataModel.getInstance().setUser(user);
                            Intent intent = new Intent(Cadastrar.this,Login.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Exception message = task.getException();
                            message.getMessage();
                            Toast.makeText(Cadastrar.this, String.valueOf(message.getMessage()),
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                        hideProgressBar();

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.buttonCadastrar){
            createAccount(editTextCadastrarEmail.getText().toString(),editTextCadastrarSenha.getText().toString());
        }
    }


    private boolean validateForm() {
        boolean valid = true;

        String nome = editTextCadastrarNome.getText().toString();
        if (TextUtils.isEmpty(nome)) {
            editTextCadastrarNome.setError("Nome necessário.");
            valid = false;
        } else {
            editTextCadastrarNome.setError(null);
        }

        String email = editTextCadastrarEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            editTextCadastrarEmail.setError("Email necessário.");
            valid = false;
        } else {
            editTextCadastrarEmail.setError(null);
        }

        String password = editTextCadastrarSenha.getText().toString();
        if (TextUtils.isEmpty(password)) {
            editTextCadastrarSenha.setError("Senha necessária.");
            valid = false;
        } else {
            editTextCadastrarSenha.setError(null);
        }

        String confiSenha = editTextCadastrarConfSenha.getText().toString();
        if (TextUtils.isEmpty(nome)) {
            editTextCadastrarConfSenha.setError("Confirmação necessária.");
            valid = false;
        } else {
            editTextCadastrarConfSenha.setError(null);
        }

        return valid;

    }

    private void showProgressBar(){
        if(progressBarCadastrar != null){
            progressBarCadastrar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar(){
        if(progressBarCadastrar != null){
            progressBarCadastrar.setVisibility(View.INVISIBLE);
        }
    }
}
