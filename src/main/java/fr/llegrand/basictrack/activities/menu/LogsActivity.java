package fr.llegrand.basictrack.activities.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.llegrand.basictrack.R;
import fr.llegrand.basictrack.models.Entrainement;
import fr.llegrand.basictrack.models.Jour;
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

        ArrayList<Entrainement> entrainements = Reader.getEntrainements();

        Map<String, List<Entrainement>> entrainementsByDate = new TreeMap<String, List<Entrainement>>(Collections.reverseOrder());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy");
        for(Entrainement e : entrainements){
            String codeDate = sdf.format(e.getDate());
            List<Entrainement> listEnt = entrainementsByDate.get(codeDate);
            if (listEnt != null) {
                listEnt.add(e);
                entrainementsByDate.replace(codeDate, listEnt);
            } else {
                List<Entrainement> newListEnt = new ArrayList<>();
                newListEnt.add(e);
                entrainementsByDate.put(codeDate, newListEnt);
            }
        }

        final Gson gson = new GsonBuilder().serializeNulls().create();
        SharedPreferences prefs = getSharedPreferences("jours", MODE_PRIVATE);
        String restoredText = prefs.getString("jours", null);
        List<Jour> jours = gson.fromJson(restoredText, new TypeToken<ArrayList<Jour>>(){}.getType());

        String c = "";
        for (String codeJour: entrainementsByDate.keySet()){
            List<Entrainement> listEnt = entrainementsByDate.get(codeJour);
            c += capitalize(codeJour) + " - " + jours.get(listEnt.get(0).getExercice().getId_jour()-1).getNom() + " :\n";
            for(Entrainement e : listEnt){
                c += "- " + e.getExercice().getNom() + " (" + e.getPosition() + (e.getPosition() == 1 ? "er" : "ème") + " exercice) :\n";
                for(Serie s : e.getSeries()) {
                    c += "            " +  s.getPoids() + " kilos : " + s.getRepetitions() + " répétitions\n";
                }
            }
            c += "\n";
        }
        if(c == "") c = "Aucun entrainement";
        ((TextView) findViewById(R.id.text)).setText(c);
    }

    public static String capitalize(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}