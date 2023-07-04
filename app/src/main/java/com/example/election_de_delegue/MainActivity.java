package com.example.election_de_delegue;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button inscription;
    private EditText mail_champs;
    private EditText mdp_connection;
    private EditText nom_connection;
    private CheckBox remenber;
    private Button forget;
    private Button valider;

    private Button inscrir;



    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom_connection = (EditText) findViewById(R.id.nom);
        mdp_connection = (EditText) findViewById(R.id.mdp);
        remenber = (CheckBox) findViewById(R.id.remenber);
        forget = (Button) findViewById(R.id.forget);
        valider = (Button) findViewById(R.id.valider);
        inscrir = (Button) findViewById(R.id.inscrir);

        valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!validateusername() | !validatepassword()){

                } else {
                    cheickUser();
                }
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Gérez le résultat de l'activité ici
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            // Traitez les données renvoyées par l'activité
                        }
                    }
                });

        inscrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, inscription_app.class);
                intent.putExtra("message", nom_connection.getText().toString());
                intent.putExtra("mdp", mdp_connection.getText().toString());
                intent.putExtra("remenber", remenber.getText().toString());
                intent.putExtra("forget", forget.getText().toString());

                launcher.launch(intent);
            }
        });
    }
    public Boolean validateusername(){
        String val = nom_connection.getText().toString();
        if (val.isEmpty()){
            nom_connection.setError("remplissez le champ");
            return false;
        } else {
            nom_connection.setError(null);
            return true;
        }
    }

    public Boolean validatepassword(){
        String val = mdp_connection.getText().toString();
        if (val.isEmpty()){
            mdp_connection.setError("remplissez le champ");
            return false;
        } else {
            mdp_connection.setError(null);
            return true;
        }
    }

    public void cheickUser(){
        String nom = nom_connection.getText().toString().trim();
        String misuser = mdp_connection.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("nom_inscription").equalTo(nom);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    nom_connection.setError(null);
                    String passwordFromDB = snapshot.child(nom).child("mdp").getValue(String.class);
                    String nomFromDB = snapshot.child(nom).child("nom_inscription").getValue(String.class);
                    String mailFromDB = snapshot.child(nom).child("mail").getValue(String.class);

                    if (Objects.equals(passwordFromDB, misuser)) {
                        nom_connection.setError(null);
                        Intent intent = new Intent(MainActivity.this, deuxieme_activite.class);
                        intent.putExtra("nom_utilisateur", nomFromDB);
                        intent.putExtra("mail_utilisateur", mailFromDB);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Votre mot de passe est incorrect!",Toast.LENGTH_SHORT).show();
                        mdp_connection.setError("Mot de passe incorrect");
                        mdp_connection.requestFocus();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Aucune personne retrouver!",Toast.LENGTH_SHORT).show();
                    nom_connection.setError("aucune personne identifiée");
                    nom_connection.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}