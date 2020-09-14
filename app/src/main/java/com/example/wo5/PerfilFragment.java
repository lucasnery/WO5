package com.example.wo5;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    ImageButton imageButtonPen;
    ImageButton imageButtonPhoto;
    FirebaseUser user;
    private final String TAG = "PerfilFragment";
    TextView NomePerfilFrag;
    TextView textViewExcluirConta;
    TextView editTextEmailPerfilFrag;
    TextView textViewRecuperacaoSenha;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.perfil_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButtonPen = view.findViewById(R.id.imageButtonPen);
        imageButtonPhoto = view.findViewById(R.id.imageButtonPen);
        NomePerfilFrag = view.findViewById(R.id.NomePerfilFrag);
        textViewExcluirConta = view.findViewById(R.id.textViewExcluirConta);
        editTextEmailPerfilFrag = view.findViewById(R.id.editTextEmailPerfilFrag);
        textViewRecuperacaoSenha = view.findViewById(R.id.textViewRecuperacaoSenha);
        user = FirebaseAuth.getInstance().getCurrentUser();
        DateTime datetimeSenha = DataModel.getInstance().getRecuperarSenhaDatetime();
        if(datetimeSenha != null){
            textViewRecuperacaoSenha.setText(datetimeSenha.getTime()  + " " +  datetimeSenha.getDate());
        }

        getFirebaseUser();

        imageButtonPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();

            }
        });
        textViewExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Deseja realmente excluir sua conta?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteUser();
                                Intent intent = new Intent(getContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Não",null)
                        .show();
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    public void deleteUser(){
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),"Conta excluída", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "User account deleted.");
                        }
                    }
                });
    }


    public void updatePassword(){
        String newPassword = "SOME-SECURE-PASSWORD";

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                        }
                    }
                });
    }

    public void getFirebaseUser(){
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            NomePerfilFrag.setText(name);
            editTextEmailPerfilFrag.setText(email);



            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
    public void updateProfile(){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(NomePerfilFrag.getText().toString())
                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            Toast.makeText(getContext(),"Perfil atualizado.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
