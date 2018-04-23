package fr.llegrand.basictrack.activities.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

import fr.llegrand.basictrack.R;
import fr.llegrand.basictrack.models.Entrainement;
import fr.llegrand.basictrack.models.Serie;
import fr.llegrand.basictrack.util.Reader;

public class LogsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_logs);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });

        ArrayList<Entrainement> entrainements = entrainements = Reader.getEntrainements();

        String c = "";
        for(Entrainement e : entrainements){
            c += "- " + e.getDate() + " : " + e.getExercice().getNom() + " (" + e.getPosition() + ")\n";
            for(Serie s : e.getSeries()){
                c += "\t" + s.getRepetitions() + "reps " + s.getPoids() + "kgs\n";
            }
        }
        if(c == "") c = "Aucun entrainement";
        ((TextView) findViewById(R.id.text)).setText(c);
    }
}