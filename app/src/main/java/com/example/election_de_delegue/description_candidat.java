package com.example.election_de_delegue;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class description_candidat extends AppCompatActivity {

    FirebaseDatabase database;

    DatabaseReference reference;
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
            String Nom_utilis = intent.getStringExtra("nom_utilisateur");
            String Mail_utilis = intent.getStringExtra("mail_utilisateur");
            String Nom_cand = intent.getStringExtra("presentation_candidat");
            String Slogan_cand = intent.getStringExtra("discour_candidat");
            String Voix1 = intent.getStringExtra("voix");

            // Utilisez les données pour afficher l'image et la description dans votre activité
            ImageView image_candidat = findViewById(R.id.image_candidat);
            image_candidat.setImageResource(imageResourceId);

            TextView Nom_candidat = findViewById(R.id.Nom_candidat);
            Nom_candidat.setText(Nom_cand);

            TextView Slogan_candidat = findViewById(R.id.Slogant_candidat);
            Slogan_candidat.setText(Mail_utilis);

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
                Intent intent = getIntent();

                String Nom_utilis = intent.getStringExtra("nom_utilisateur");
                String Mail_utilis = intent.getStringExtra("mail_utilisateur");

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("votant");
                Query checkUserDatabase = reference.orderByChild("nom_inscription").equalTo(Nom_utilis);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(description_candidat.this, "Désolé, vous avez déjà voté, merci.", Toast.LENGTH_SHORT).show();
                        } else {
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("votant");

                            String nom_inscription = intent.getStringExtra("nom_utilisateur");
                            String mail = intent.getStringExtra("mail_utilisateur");
                            String nom_candidat = intent.getStringExtra("presentation_candidat");
                            Integer Vote = 1;

                            intent.putExtra("nom_utilisateur", nom_inscription);
                            intent.putExtra("mail_utilisateur", mail);

                            HlpherClassVote helperClass = new HlpherClassVote(nom_inscription, mail, Vote, nom_candidat);
                            reference.child(nom_inscription).setValue(helperClass);

                            Toast.makeText(description_candidat.this, "Vous avez voté avec succès!", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Gérer l'annulation ici
                    }
                });
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