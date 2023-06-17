package com.example.election_de_delegue;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class description_candidat extends AppCompatActivity {
    private TextView Nom_candidat;
    private ImageView image_candidat;
    private TextView Slogant_candidat;
    private Button voter;
    private Button statistique;
    private  TextView voix;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_candidat);

        image_candidat = (ImageView) findViewById(R.id.image_candidat);
        Slogant_candidat = (TextView) findViewById(R.id.Slogant_candidat);
        Nom_candidat = ((TextView) findViewById(R.id.Nom_candidat));
        voix = ((TextView) findViewById(R.id.voix));
        voter = (Button) findViewById(R.id.voter);
        statistique = (Button) findViewById(R.id.statistique);

        Intent intent = getIntent();
        if (intent != null) {
            int imageResourceId = intent.getIntExtra("image_candidat", 0);
            String Nom_cand = intent.getStringExtra("presentation_candidat");
            String Slogan_cand = intent.getStringExtra("discour_candidat");
            String Voix1 = intent.getStringExtra("voix");

            // Utilisez les données pour afficher l'image et la description dans votre activité
            ImageView image_candidat = findViewById(R.id.image_candidat);
            image_candidat.setImageResource(imageResourceId);

            TextView Nom_candidat = findViewById(R.id.Nom_candidat);
            Nom_candidat.setText(Nom_cand);

            TextView Slogan_candidat = findViewById(R.id.Slogant_candidat);
            Slogan_candidat.setText(Slogan_cand);

        }


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
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

        voter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView Voix = findViewById(R.id.voix);
                String voix = Voix.getText().toString().trim();
                int voixdb = Integer.parseInt(voix);
                int score = 0;
                Context description_candidat;
                launcher.launch(intent);

            }
        });

        statistique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imageResourceId = R.mipmap.image1_foreground;
                Intent intent = new Intent(description_candidat.this, MainActivity.class);

                launcher.launch(intent);
            }
        });

    }
}