package fr.llegrand.basictrack.activities.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import fr.llegrand.basictrack.R;
import fr.llegrand.basictrack.models.Entrainement;
import fr.llegrand.basictrack.util.Reader;

public class EditDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_editdata);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });

        ((Button) findViewById(R.id.btnExport)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "eeee");

                try {
                    ArrayList<Entrainement> entrainements = Reader.getEntrainements();
                    String fileName = Reader.exportToCSV(entrainements);

                    Toast.makeText(getApplicationContext(), "Données exportées (" + fileName + ")", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Erreur: Permission d'écriture nécessaire", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}