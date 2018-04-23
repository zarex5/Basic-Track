package fr.llegrand.basictrack.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.llegrand.basictrack.R;
import fr.llegrand.basictrack.activities.menu.AboutActivity;
import fr.llegrand.basictrack.activities.menu.EditDataActivity;
import fr.llegrand.basictrack.activities.menu.EditDaysActivity;
import fr.llegrand.basictrack.activities.menu.EditExosActivity;
import fr.llegrand.basictrack.activities.menu.LogsActivity;
import fr.llegrand.basictrack.adapters.ViewPagerAdapter;
import fr.llegrand.basictrack.models.Exercice;
import fr.llegrand.basictrack.models.Jour;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    private Toolbar toolBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    List<Jour> jours;
    List<Exercice> exercices;

    /*A la création de l'activité*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        getData();

        toolBar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolBar);

        viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(tabListener);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getData();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    /*Setup fake données*/
    private void getData() {
        final Gson gson = new GsonBuilder().serializeNulls().create();

        if(getSharedPreferences("jours", MODE_PRIVATE).getString("jours", null) == null) {
            Log.e("DAT", "SharedPreferences vide");
            ArrayList j = new ArrayList<Jour>();
            j.add(new Jour(1, "Pectoraux", 1));
            j.add(new Jour(2, "Dorsaux", 2));
            j.add(new Jour(3, "Jambes", 3));

            SharedPreferences.Editor editor = getSharedPreferences("jours", MODE_PRIVATE).edit();
            editor.putString("jours", gson.toJson(j).toString());
            editor.apply();
        }

        SharedPreferences prefs = getSharedPreferences("jours", MODE_PRIVATE);
        String restoredText = prefs.getString("jours", null);
        jours = gson.fromJson(restoredText, new TypeToken<ArrayList<Jour>>(){}.getType());

        exercices = new ArrayList<>();
        exercices.add(new Exercice(1, "Développé couché", "PECTORAUX", 4, 10, 25.0, 1, 1));
        exercices.add(new Exercice(2, "Développé couché incliné", "PECTORAUX", 4, 10, 20.0, 2, 1));
        exercices.add(new Exercice(3, "Écarté couché haltères", "PECTORAUX", 4, 10, 5.0, 3, 1));
        exercices.add(new Exercice(4, "Curl haltère", "BICEPS", 4, 3, 10, 4, 1));
        exercices.add(new Exercice(5, "Drag curl", "BICEPS", 3, 10, 15, 5, 1));
        exercices.add(new Exercice(6, "Curl concentration", "BICEPS", 3, 10, 8, 6, 1));
        exercices.add(new Exercice(7, "Tapis de course", "CARDIO", 1, 1, 2, 7, 1));
        exercices.add(new Exercice(8, "Tirage poitrine large", "DORSAUX", 4, 10, 34.3, 1, 2));
        exercices.add(new Exercice(9, "Tirage poitrine sérrée", "DORSAUX", 4, 10, 34.3, 2, 2));
        exercices.add(new Exercice(10, "Rowing barre", "DORSAUX", 4, 10, 27.3, 3, 2));
        exercices.add(new Exercice(11, "Tirage sol poulie", "DORSAUX", 4, 10, 27.3, 4, 2));
        exercices.add(new Exercice(12, "Poulie haute pronation", "TRICEPS", 3, 10, 9, 5, 2));
        exercices.add(new Exercice(13, "Extension dessus tête", "TRICEPS", 3, 10, 5, 6, 2));
        exercices.add(new Exercice(14, "Dips", "TRICEPS", 3, 10, 37, 7, 2));
        exercices.add(new Exercice(15, "Fentes", "FESSIERS", 3, 8, 16.0, 1, 3));
        exercices.add(new Exercice(16, "Presse", "FESSIERS", 4, 8, 50.0, 2, 3));
        exercices.add(new Exercice(17, "Leg extension", "FESSIERS", 3, 10, 32.0, 3, 3));
        exercices.add(new Exercice(18, "Leg curl couché", "ISCHIOS", 3, 10, 23.0, 4, 3));
        exercices.add(new Exercice(19, "Calf raises", "MOLLETS", 3, 15, 43.0, 5, 3));
        exercices.add(new Exercice(20, "Écarter", "ABDUCTEURS", 3, 10, 32.0, 6, 3));
        exercices.add(new Exercice(21, "Rapprocher", "ABDUCTEURS", 3, 10, 32.0, 7, 3));
        exercices.add(new Exercice(22, "Abdos", "ABDOS", 3, 15, 20, 8, 3));
    }

    /*Setup des onglets des tabs*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Collections.sort(this.jours);
        for(int i = 0; i<jours.size(); i++) {
            ArrayList<Exercice> exercicesJour = new ArrayList<>();
            for(Exercice e : exercices) {
                if (e.getId_jour() == jours.get(i).getId())
                    exercicesJour.add(e);
            }
            adapter.addFrag(new TabFragment(this, exercicesJour), jours.get(i).getNom());
        }
        viewPager.setAdapter(adapter);
    }

    /*A la création du menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*A la sélection d'un onglet*/
    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            switch (tab.getPosition()) {
                case 0:
                    Log.i("DEBUG", "TAB1 SELECTED");
                    break;
                case 1:
                    Log.i("DEBUG", "TAB2 SELECTED");
                    break;
                case 2:
                    Log.i("DEBUG", "TAB3 SELECTED");
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    /*Au clic sur un bouton du menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logs:
                Log.i("DEBUG", "LOGS CLICKED");
                Intent versLogs = new Intent(getApplicationContext(), LogsActivity.class);
                getApplicationContext().startActivity(versLogs);
                return true;

            case R.id.action_edit_days:
                Log.i("DEBUG", "EDIT DAYS CLICKED");
                Intent versEditDays = new Intent(getApplicationContext(), EditDaysActivity.class);
                getApplicationContext().startActivity(versEditDays);
                return true;

            case R.id.action_edit_exercices:
                Log.i("DEBUG", "EDIT EXOS CLICKED");
                Intent versEditExos = new Intent(getApplicationContext(), EditExosActivity.class);
                getApplicationContext().startActivity(versEditExos);
                return true;

            case R.id.action_edit_data:
                Log.i("DEBUG", "EDIT DATA CLICKED");
                Intent versEditData = new Intent(getApplicationContext(), EditDataActivity.class);
                getApplicationContext().startActivity(versEditData);
                return true;

            case R.id.action_about:
                Log.i("DEBUG", "ABOUT CLICKED");
                Intent versAbout = new Intent(getApplicationContext(), AboutActivity.class);
                getApplicationContext().startActivity(versAbout);
                return true;

            default:
                Log.i("DEBUG", "??? CLICKED");
                return super.onOptionsItemSelected(item);
        }
    }
}