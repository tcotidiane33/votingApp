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

    private EditText nom_inscri;

    private EditText mail_in;

    private EditText mdp_inscri;

    private EditText mdp_confirmation_inscri;

    private Button ok_valider;

    private Button deja_inscri_connecter;

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_app);

        id_permane = (EditText) findViewById(R.id.id_permane);
        mdp_inscri = (EditText) findViewById(R.id.mdp);
        deja_inscri_connecter = (Button) findViewById(R.id.deja_inscri_connecter);
        ok_valider = (Button) findViewById(R.id.ok_valider);
        mdp_confirmation_inscri = (EditText) findViewById(R.id.mdp_confirmation);
        nom_inscri = (EditText) findViewById(R.id.nom_inscription);
        mail_in = (EditText) findViewById(R.id.mail);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Code à exécuter après le retour de l'activité lancée
                        if (result.getResultCode() == RESULT_OK) {
                            // Le résultat est OK
                            Intent intent = result.getData();
                            // Traitez les données ou effectuez d'autres actions nécessaires
                        }
                    }
                });

        ok_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String nom_inscription = nom_inscri.getText().toString().trim();
                String mail = mail_in.getText().toString().trim();
                String mdp = mdp_inscri.getText().toString().trim();
                String mdp_confirmation = mdp_confirmation_inscri.getText().toString().trim();

                HelperClass helperClass = new HelperClass(nom_inscription,mail,mdp,mdp_confirmation);
                reference.child(nom_inscription).setValue(helperClass);

                Toast.makeText(inscription_app.this, "Vous êtes bien inscri(te) avec sucess!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(inscription_app.this, MainActivity.class);
                startActivity(intent);
            }
        });

        deja_inscri_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inscription_app.this, MainActivity.class);
                intent.putExtra("message", id_permane.getText().toString());
                intent.putExtra("mdp", mdp_inscri.getText().toString());
                intent.putExtra("mdp_confirmaton", mdp_confirmation_inscri.getText().toString());
                intent.putExtra("nom_inscription", nom_inscri.getText().toString());
                intent.putExtra("prenoms_inscription", mail_in.getText().toString());
                intent.putExtra("deja_inscri_connecter", deja_inscri_connecter.getText().toString());

                launcher.launch(intent);
            }
        });

    }
}