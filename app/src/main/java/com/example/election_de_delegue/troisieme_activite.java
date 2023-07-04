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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.election_de_delegue.databinding.ActivityTroisiemeActiviteBinding;

public class troisieme_activite extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTroisiemeActiviteBinding binding;

    private TextView but_niveau;
    private ImageView image_candidat;
    private ImageView image_candidat1;

    private ImageView deuxieme_image_candidat;
    private ImageView deuxieme_image_candidat1;
    private TextView presentation_candidat;
    private TextView discour_candidat;
    private LinearLayout champ_candidat;

    private LinearLayout champ_candidat_2;
    private TextView deuxieme_presentation_candidat;
    private TextView deuxieme_discour_candidat;

    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troisieme_activite);

        image_candidat = (ImageView) findViewById(R.id.image_candidat);
        image_candidat1 = (ImageView) findViewById(R.id.image_candidat1);
        deuxieme_image_candidat = (ImageView) findViewById(R.id.deuxieme_image_candidat);
        deuxieme_image_candidat1 = (ImageView) findViewById(R.id.deuxieme_image_candidat1);
        presentation_candidat = (TextView) findViewById(R.id.presentation_candidat);
        but_niveau = (TextView) findViewById(R.id.but_niveau);
        deuxieme_presentation_candidat = (TextView) findViewById(R.id.deuxieme_presentation_candidat);
        discour_candidat = (TextView) findViewById(R.id.discour_candidat);
        deuxieme_discour_candidat = (TextView) findViewById(R.id.deuxieme_discour_candidat);
        champ_candidat = (LinearLayout) findViewById(R.id.champs_candidat);
        champ_candidat_2 = (LinearLayout) findViewById(R.id.champs_candidat_2);


        Intent intent = getIntent(); // Récupérer l'intent qui a déclenché cette activité
        String Nom_utilis = intent.getStringExtra("nom_utilisateur");
        String Mail_utilis = intent.getStringExtra("mail_utilisateur");
        TextView Nom_utilisateur = findViewById(R.id.but_niveau);
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

        champ_candidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imageResourceId = R.mipmap.image0_foreground;
                Intent intent = new Intent(troisieme_activite.this, description_candidat.class);

                String Nom_utilisateur = intent.getStringExtra("nom_utilisateur");
                String Mail_utilisateur = intent.getStringExtra("mail_utilisateur");

                intent.putExtra("nom_utilisateur", Nom_utilis);
                intent.putExtra("mail_utilisateur", Mail_utilis);

                intent.putExtra("image_candidat", imageResourceId);
                intent.putExtra("presentation_candidat", presentation_candidat.getText().toString());
                intent.putExtra("discour_candidat", discour_candidat.getText().toString());
                intent.putExtra("champ_candidat", champ_candidat.getLayoutParams().toString());

                launcher.launch(intent);
            }
        });

        champ_candidat_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imageResourceId = R.mipmap.image1_foreground;
                Intent intent = new Intent(troisieme_activite.this, description_candidat.class);

                String Nom_utilisatueur = intent.getStringExtra("nom_utilisateur");
                String Mail_utilisateur = intent.getStringExtra("mail_utilisateur");

                intent.putExtra("nom_utilisateur", Nom_utilisatueur);
                intent.putExtra("mail_utilisateur", Mail_utilisateur);

                intent.putExtra("image_candidat", imageResourceId);
                intent.putExtra("presentation_candidat", deuxieme_presentation_candidat.getText().toString());
                intent.putExtra("discour_candidat", deuxieme_discour_candidat.getText().toString());
                intent.putExtra("champ_candidat_2", champ_candidat.getLayoutParams().toString());

                launcher.launch(intent);
            }
        });
    }
}