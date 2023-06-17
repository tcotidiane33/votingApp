package com.example.election_de_delegue;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private final static int SECONDE_CALL_ACTIVITY = 1234;

    private Button inscription;
    private EditText id_permane;
    private EditText mdp;
    private CheckBox remenber;
    private Button forget;
    private Button valider;

    private Button inscrir;



    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_permane = (EditText) findViewById(R.id.id_permane);
        mdp = (EditText) findViewById(R.id.mdp);
        remenber = (CheckBox) findViewById(R.id.remenber);
        forget = (Button) findViewById(R.id.forget);
        valider = (Button) findViewById(R.id.valider);
        inscrir = (Button) findViewById(R.id.inscrir);
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

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, deuxieme_activite.class);
                intent.putExtra("message", id_permane.getText().toString());
                intent.putExtra("mdp", mdp.getText().toString());
                intent.putExtra("remenber", remenber.getText().toString());
                intent.putExtra("forget", forget.getText().toString());

                launcher.launch(intent);
            }
        });

        inscrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, inscription_app.class);
                intent.putExtra("message", id_permane.getText().toString());
                intent.putExtra("mdp", mdp.getText().toString());
                intent.putExtra("remenber", remenber.getText().toString());
                intent.putExtra("forget", forget.getText().toString());

                launcher.launch(intent);
            }
        });
    }
}


