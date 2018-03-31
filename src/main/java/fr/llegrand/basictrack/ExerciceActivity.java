package fr.llegrand.basictrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
            exercice = (Exercice) getIntent().getSerializableExtra("exercice");
        }

        ((TextView)findViewById(R.id.toolbar_exercice_nom)).setText(exercice.getNom());

        ((TextView)findViewById(R.id.toolbar_exercice_muscle)).setText(exercice.getMuscle());

        ((TextView)findViewById(R.id.reps_input_1)).setText(Integer.toString(exercice.getRepetitions()));
        ((TextView)findViewById(R.id.reps_input_2)).setText(Integer.toString(exercice.getRepetitions()));
        ((TextView)findViewById(R.id.reps_input_3)).setText(Integer.toString(exercice.getRepetitions()));

        ((TextView)findViewById(R.id.kg_input_1)).setText(Double.toString(exercice.getPoids()));
        ((TextView)findViewById(R.id.kg_input_2)).setText(Double.toString(exercice.getPoids()));
        ((TextView)findViewById(R.id.kg_input_3)).setText(Double.toString(exercice.getPoids()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercice);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });

        ImageView iv2 = (ImageView) findViewById(R.id.delete_img_2);
        iv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Log.i("EXO", "Delete serie 2");
                CardView cv = (CardView)findViewById(R.id.cardview_2);
                cv.removeAllViews();
            }
        });
        ImageView iv3 = (ImageView) findViewById(R.id.delete_img_3);
        iv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Log.i("EXO", "Delete serie 3");
                CardView cv = (CardView)findViewById(R.id.cardview_3);
                cv.removeAllViews();
            }
        });
    }


}
