package com.example.wo5;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wo5.databinding.LoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.example.wo5.databinding.login;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Login";
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private LoginBinding mBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = LoginBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(mBinding.getRoot());
        setProgressBar(mBinding.progressBarEntrar);
        mBinding.buttonEntrar.setOnClickListener(this);
        mBinding.buttonCadastrar.setOnClickListener(this);
        mBinding.textViewEsqueceu.setOnClickListener(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        mBinding.textViewEsqueceu.setOnClickListener(this);
        mBinding.buttonRecuperarSenha.setOnClickListener(this);
    }
    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //updateUI(mAuth.getCurrentUser());
    }
    // [END on_start_check_user]

    //Methods

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonCadastrar) {
            createAccount(mBinding.editTextEmail.getText().toString(), mBinding.editTextPassword.getText().toString());
        } else if (i == R.id.buttonEntrar) {
            signIn(mBinding.editTextEmail.getText().toString(), mBinding.editTextPassword.getText().toString());
        }else if (i == R.id.textViewEsqueceu){
            mBinding.editTextPassword.setVisibility(View.INVISIBLE);
            mBinding.buttonCadastrar.setVisibility(View.INVISIBLE);
            mBinding.buttonEntrar.setVisibility(View.INVISIBLE);
            mBinding.textViewEsqueceu.setVisibility(View.INVISIBLE);
            mBinding.checkBoxReceber.setVisibility(View.INVISIBLE);
            mBinding.buttonRecuperarSenha.setVisibility(View.VISIBLE);
        }else if(i == R.id.buttonRecuperarSenha){
            String email = String.valueOf(mBinding.editTextEmail.getText());
            resetPassword(email);
            Toast.makeText(Login.this, "Você receberá um email de recuperação de senha.",
                    Toast.LENGTH_LONG).show();
            mBinding.editTextPassword.setVisibility(View.VISIBLE);
            mBinding.buttonCadastrar.setVisibility(View.VISIBLE);
            mBinding.buttonEntrar.setVisibility(View.VISIBLE);
            mBinding.textViewEsqueceu.setVisibility(View.VISIBLE);
            mBinding.checkBoxReceber.setVisibility(View.VISIBLE);
            mBinding.buttonRecuperarSenha.setVisibility(View.INVISIBLE);
        }
        // else if (i == R.id.signOutButton) {
        //    signOut();
        //}
        //else if (i == R.id.verifyEmailButton) {
        //    sendEmailVerification();
        //} else if (i == R.id.reloadButton) {
        //    reload();
        //}
    }

    private void createAccount(final String email, final String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        showProgressBar();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            DataModel.getInstance().saveLog(TAG + " createUserWithEmail" + mAuth.getCurrentUser());
                            Toast.makeText(Login.this, "Sucesso no Cadastro.",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            signIn(email,password);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure");
                            DataModel.getInstance().saveLog(TAG + "createUserWithEmail:failure");
                            Toast.makeText(Login.this, "Falha no Cadastro",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressBar();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            DataModel.getInstance().saveLog(TAG + "signInWithEmail:success" + mAuth.getCurrentUser());
                            Toast.makeText(Login.this,  "Sucesso no Acesso.",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(Login.this, InicioLogado.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            DataModel.getInstance().saveLog(TAG + "signInWithEmail:failure" +  task.getException());
                            Toast.makeText(Login.this,  "Falha no Acesso.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                            // [START_EXCLUDE]
                            //checkForMultiFactorFailure(task.getException());
                            // [END_EXCLUDE]
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //mBinding.status.setText(R.string.auth_failed);
                        }
                        hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    public void resetPassword(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email;
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "Email sent");
                        }
                    }
                });

    }

    /*private void sendEmailVerification() {
        // Disable button
        mBinding.verifyEmailButton.setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        mBinding.verifyEmailButton.setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(EmailPasswordActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }*/

    private void reload() {
        mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    updateUI(mAuth.getCurrentUser());
                    Toast.makeText(Login.this,"Reload successful!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "reload", task.getException());
                    Toast.makeText(Login.this,
                            "Failed to reload user.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mBinding.editTextEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.editTextEmail.setError("Email necessário.");
            valid = false;
        } else {
            mBinding.editTextEmail.setError(null);
        }

        String password = mBinding.editTextPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.editTextPassword.setError("Senha necessária.");
            valid = false;
        } else {
            mBinding.editTextPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        hideProgressBar();

        if (user == null) {
            //Toast.makeText(Login.this,"Usuário desconhecido",
            //        Toast.LENGTH_SHORT).show();
            //mBinding.status.setText(getString(R.string.emailpassword_status_fmt,
            //        user.getEmail(), user.isEmailVerified()));
            //mBinding.detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            //mBinding.emailPasswordButtons.setVisibility(View.GONE);
            //mBinding.editTextPassword.setVisibility(View.GONE);
            //mBinding.signedInButtons.setVisibility(View.VISIBLE);

            //if (user.isEmailVerified()) {
                //mBinding.verifyEmailButton.setVisibility(View.GONE);
            //} else {
                //mBinding.verifyEmailButton.setVisibility(View.VISIBLE);
            //}
        } else {
            //Toast.makeText(Login.this,"Usuário Logado",
              //      Toast.LENGTH_SHORT).show();

            //mBinding.status.setText(R.string.signed_out);
           // mBinding.detail.setText(null);
            //mBinding.emailPasswordButtons.setVisibility(View.VISIBLE);
            //mBinding.emailPasswordFields.setVisibility(View.VISIBLE);
           // mBinding.signedInButtons.setVisibility(View.GONE);
        }
    }



    private void setProgressBar(ProgressBar progressBarEntrar) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = DataModel.getInstance().getUser();
        if(user != null){

        }
    }





    private void showProgressBar(){
        if(mBinding.progressBarEntrar != null){
            mBinding.progressBarEntrar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar(){
        if(mBinding.progressBarEntrar != null){
            mBinding.progressBarEntrar.setVisibility(View.INVISIBLE);
        }
    }



}
