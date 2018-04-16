package fr.llegrand.basictrack;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fr.llegrand.basictrack.models.Entrainement;
import fr.llegrand.basictrack.models.Exercice;
import fr.llegrand.basictrack.models.Serie;

public class ExerciceActivity extends AppCompatActivity {
    Exercice exercice = null;

    final int[] cardsId = new int[]{R.id.cardview_1, R.id.cardview_2, R.id.cardview_3, R.id.cardview_4, R.id.cardview_5};
    final int[] repsId = new int[]{R.id.reps_input_1, R.id.reps_input_2, R.id.reps_input_3, R.id.reps_input_4, R.id.reps_input_5};
    final int[] kgsId = new int[]{R.id.kg_input_1, R.id.kg_input_2, R.id.kg_input_3, R.id.kg_input_4, R.id.kg_input_5};
    final int[] deleteId = new int[]{R.id.delete_img_1, R.id.delete_img_2, R.id.delete_img_3, R.id.delete_img_4, R.id.delete_img_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            exercice = (Exercice) getIntent().getSerializableExtra("exercice");
        }

        ((TextView) findViewById(R.id.toolbar_exercice_nom)).setText(exercice.getNom());

        ((TextView) findViewById(R.id.toolbar_exercice_muscle)).setText(exercice.getMuscle());

        for (int i = 0; i<exercice.getSeries(); i++) {
            findViewById(cardsId[i]).setVisibility(View.VISIBLE);
            ((TextView) findViewById(repsId[i])).setText(Integer.toString(exercice.getRepetitions()));
            ((TextView) findViewById(kgsId[i])).setText(Double.toString(exercice.getPoids()));

            if (i != 0) {
                final int nb = i;
                ((ImageView) findViewById(deleteId[i])).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i("EXO", "Delete serie " + (nb + 1));
                        CardView cv = (CardView) findViewById(cardsId[nb]);
                        cv.setVisibility(View.GONE);
                        cv.removeAllViews();
                    }
                });
            } else {
                ((ImageView) findViewById(deleteId[i])).setVisibility(View.INVISIBLE);
            }
        }

        ((Button) findViewById(R.id.btn_valider)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submit();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercice);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });
    }

    public void submit() {
        ArrayList<Serie> series = new ArrayList<>();
        for (int i = 0; i<exercice.getSeries(); i++) {
            if(findViewById(cardsId[i]).getVisibility() == View.VISIBLE) {
                int reps = Integer.parseInt(((TextView) findViewById(repsId[i])).getText().toString());
                double kgs = Double.parseDouble(((TextView) findViewById(kgsId[i])).getText().toString());
                series.add(new Serie(reps, kgs));
            }
        }
        Entrainement e = new Entrainement(exercice, series);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        File directory = new File(Environment.getExternalStorageDirectory()+"/Basic-Track/entrainements/");
        if (! directory.exists())
            directory.mkdirs();
        File file = new File(directory, "ent-"+exercice.getId()+"-"+e.getDate().getTime());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(e);
            os.close();
            fos.close();
        } catch (IOException err) {
            err.printStackTrace();
        }

        onBackPressed();
        Toast.makeText(getApplicationContext(), "Exercice ajouté", Toast.LENGTH_SHORT).show();
    }
}