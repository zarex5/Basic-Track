package fr.llegrand.basictrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.llegrand.basictrack.models.Exercice;

public class ExerciceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        Exercice exercice = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            exercice = (Exercice) getIntent().getSerializableExtra("exo");
        }

        TextView nomToolbar = (TextView)findViewById(R.id.toolbar_exercice_nom);
        nomToolbar.setText(exercice.getNom());
        TextView muscleToolbar = (TextView)findViewById(R.id.toolbar_exercice_muscle);
        muscleToolbar.setText(exercice.getMuscle());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercice);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });
    }
}
