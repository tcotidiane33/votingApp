package com.example.election_de_delegue;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.election_de_delegue.databinding.ActivityDeuxiemeActiviteBinding;

public class deuxieme_activite extends AppCompatActivity {
    private final static int TROISEME_CALL_ACTIVITY = 1234;
    private TextView nom_utilisateur;
    private Button licence1;
    private Button licence2;
    private Button licence3;

    private Button master1;
    private Button master2;

    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deuxieme_activite);
        nom_utilisateur = (TextView) findViewById(R.id.nom_utilisateur);
        licence1 = (Button) findViewById(R.id.licence1);
        licence2 = (Button) findViewById(R.id.licence2);
        licence3 = (Button) findViewById(R.id.licence3);
        master1 = (Button) findViewById(R.id.master1);
        master2 = (Button) findViewById(R.id.master2);


        Intent intent = getIntent(); // Récupérer l'intent qui a déclenché cette activité
        String Nom_utilis = intent.getStringExtra("nom_utilisateur");
        String Mail_utilis = intent.getStringExtra("mail_utilisateur");
        TextView Nom_utilisateur = findViewById(R.id.nom_utilisateur);
        Nom_utilisateur.setText(Nom_utilis);


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

        licence1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deuxieme_activite.this, troisieme_activite.class);
                String Nom_utilisatueur = Nom_utilis;
                String Mail_utilisateur = Mail_utilis;

                intent.putExtra("nom_utilisateur", Nom_utilisatueur);
                intent.putExtra("mail_utilisateur", Mail_utilisateur);

                intent.putExtra("licence1", licence1.getText().toString());
                intent.putExtra("licence2", licence2.getText().toString());
                intent.putExtra("licence3", licence3.getText().toString());
                intent.putExtra("master1", master1.getText().toString());
                intent.putExtra("master2", master2.getText().toString());

                launcher.launch(intent);
            }
        });

        licence2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deuxieme_activite.this, activite_licence2.class);
                String Nom_utilisatueur = intent.getStringExtra("nom_utilisateur");
                String Mail_utilisateur = intent.getStringExtra("mail_utilisateur");

                intent.putExtra("nom_utilisateur", Nom_utilisatueur);
                intent.putExtra("mail_utilisateur", Mail_utilisateur);

                intent.putExtra("licence1", licence1.getText().toString());
                intent.putExtra("licence2", licence2.getText().toString());
                intent.putExtra("licence3", licence3.getText().toString());
                intent.putExtra("master1", master1.getText().toString());
                intent.putExtra("master2", master2.getText().toString());

                launcher.launch(intent);
            }
        });

        licence3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deuxieme_activite.this, activite_licence3.class);
                String Nom_utilisatueur = intent.getStringExtra("nom_utilisateur");
                String Mail_utilisateur = intent.getStringExtra("mail_utilisateur");

                intent.putExtra("nom_utilisateur", Nom_utilisatueur);
                intent.putExtra("mail_utilisateur", Mail_utilisateur);

                intent.putExtra("licence1", licence1.getText().toString());
                intent.putExtra("licence2", licence2.getText().toString());
                intent.putExtra("licence3", licence3.getText().toString());
                intent.putExtra("master1", master1.getText().toString());
                intent.putExtra("master2", master2.getText().toString());

                launcher.launch(intent);
            }
        });


        master1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deuxieme_activite.this, activite_master1.class);
                String Nom_utilisatueur = intent.getStringExtra("nom_utilisateur");
                String Mail_utilisateur = intent.getStringExtra("mail_utilisateur");

                intent.putExtra("nom_utilisateur", Nom_utilisatueur);
                intent.putExtra("mail_utilisateur", Mail_utilisateur);

                intent.putExtra("licence1", licence1.getText().toString());
                intent.putExtra("licence2", licence2.getText().toString());
                intent.putExtra("licence3", licence3.getText().toString());
                intent.putExtra("master1", master1.getText().toString());
                intent.putExtra("master2", master2.getText().toString());

                launcher.launch(intent);
            }
        });

        master2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deuxieme_activite.this, activite_master2.class);
                String Nom_utilisatueur = intent.getStringExtra("nom_utilisateur");
                String Mail_utilisateur = intent.getStringExtra("mail_utilisateur");

                intent.putExtra("nom_utilisateur", Nom_utilisatueur);
                intent.putExtra("mail_utilisateur", Mail_utilisateur);

                intent.putExtra("licence1", licence1.getText().toString());
                intent.putExtra("licence2", licence2.getText().toString());
                intent.putExtra("licence3", licence3.getText().toString());
                intent.putExtra("master1", master1.getText().toString());
                intent.putExtra("master2", master2.getText().toString());

                launcher.launch(intent);
            }
        });
    }
}