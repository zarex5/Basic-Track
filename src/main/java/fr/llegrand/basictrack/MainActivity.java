package fr.llegrand.basictrack;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.llegrand.basictrack.adapters.ViewPagerAdapter;
import fr.llegrand.basictrack.models.Exercice;
import fr.llegrand.basictrack.models.Jour;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    //private NotificationManagerCompat notificationManager = null;
    //private NotificationCompat.Builder mBuilder = null;
    List<Jour> jours;
    List<Exercice> exercices;

    /*A la création de l'activité*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        toolBar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolBar);

        viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(tabListener);

        /*Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        mBuilder = new NotificationCompat.Builder(this, "1")
                .setContentTitle("Selection")
                .setContentText("Tab2 selectionnée")
                .setSmallIcon(R.drawable.ic_smallicon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManager = NotificationManagerCompat.from(this);*/
    }

    /*Setup fake données*/
    private void getData() {
        jours = new ArrayList<>();
        jours.add(new Jour(1, "Lundi", 1));
        jours.add(new Jour(2, "Mardi", 2));
        jours.add(new Jour(3, "Jeudi", 3));

        exercices = new ArrayList<>();
        exercices.add(new Exercice(1, "Développé couché", "PECTORAUX", 4, 10, 20.0, 1, 1));
        exercices.add(new Exercice(2, "Développé couché incliné", "PECTORAUX", 4, 10, 10.0, 2, 1));
        exercices.add(new Exercice(3, "Curl haltère", "BICEPS", 3, 10, 8.0, 3, 1));
        exercices.add(new Exercice(4, "Presse", "FESSIERS", 4, 8, 60, 1, 2));
        exercices.add(new Exercice(5, "Tirage large", "DORSAUX", 4, 10, 25, 1, 3));
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
                    //Toast toast = Toast.makeText(getApplicationContext(), "Page1", Toast.LENGTH_SHORT);
                    //toast.show();
                    Log.i("DEBUG", "TAB1 SELECTED");
                    break;
                case 1:
                    //notificationManager.notify(1, mBuilder.build());
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
            case R.id.action_settings:
                Log.i("DEBUG", "SETTINGS CLICKED");
                return true;

            case R.id.action_plus:
                Log.i("DEBUG", "PLUS CLICKED");
                return true;

            default:
                Log.i("DEBUG", "??? CLICKED");
                return super.onOptionsItemSelected(item);
        }
    }
}