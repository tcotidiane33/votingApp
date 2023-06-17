package com.example.election_de_delegue;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Confirmation_vote extends AppCompatActivity {

    private TextView voteo;
    private DatabaseManager DatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_vote);

        voteo = (TextView) findViewById(R.id.voteo);
        DatabaseManager = new DatabaseManager((View.OnClickListener) Confirmation_vote.this);
        Intent intent = getIntent();
        if (intent != null) {
            int imageResourceId = intent.getIntExtra("image_candidat", 0);
            String Nom_cand = intent.getStringExtra("nom_candidat");
            String Slogan_cand = intent.getStringExtra("discour_candidat");

            // Utilisez les données pour afficher l'image et la description dans votre activité

            TextView voteo = findViewById(R.id.voteo);
            voteo.setText(Nom_cand);

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
    }
}