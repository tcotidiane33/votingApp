package com.example.election_de_delegue;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inscription_app extends AppCompatActivity {

    FirebaseDatabase database;

    DatabaseReference reference;

    private EditText id_permane;

    private EditText nom_inscription;

    private EditText prenoms_inscription;

    private EditText mdp;

    private EditText mdp_confirmation;

    private Button ok_valider;

    private Button deja_inscri_connecter;

    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_app);

        id_permane = (EditText) findViewById(R.id.id_permane);
        mdp = (EditText) findViewById(R.id.mdp);
        deja_inscri_connecter = (Button) findViewById(R.id.deja_inscri_connecter);
        ok_valider = (Button) findViewById(R.id.ok_valider);
        mdp_confirmation = (EditText) findViewById(R.id.mdp_confirmation);
        nom_inscription = (EditText) findViewById(R.id.nom_inscription);
        prenoms_inscription = (EditText) findViewById(R.id.prenoms_inscription);

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

        ok_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String nom_inscri = nom_inscription.getText().toString();
                String prenoms_inscri = prenoms_inscription.toString().toString();
                String mot_de_passe = mdp.toString().toString();
                String mot_passe_conf = mdp_confirmation.toString().toString();

                HelperClass helperClass = new HelperClass(nom_inscri,prenoms_inscri,mot_de_passe,mot_passe_conf);
                reference.child(nom_inscri).setValue(helperClass);

                Toast.makeText(inscription_app.this, "Vous êtes bien inscri(te) avec sucess!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(inscription_app.this, Log.class);
                startActivity(intent);

                launcher.launch(intent);
            }
        });

        deja_inscri_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inscription_app.this, MainActivity.class);
                intent.putExtra("message", id_permane.getText().toString());
                intent.putExtra("mdp", mdp.getText().toString());
                intent.putExtra("mdp_confirmaton", mdp_confirmation.getText().toString());
                intent.putExtra("nom_inscription", nom_inscription.getText().toString());
                intent.putExtra("prenoms_inscription", prenoms_inscription.getText().toString());
                intent.putExtra("deja_inscri_connecter", deja_inscri_connecter.getText().toString());

                launcher.launch(intent);
            }
        });
    }
}