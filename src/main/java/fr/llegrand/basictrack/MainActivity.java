package fr.llegrand.basictrack;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fr.llegrand.basictrack.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    //private NotificationManagerCompat notificationManager = null;
    //private NotificationCompat.Builder mBuilder = null;

    /*A la création de l'activité*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    /*Setup des onglets des tabs*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabFragment(this), "Lundi");
        adapter.addFrag(new TabFragment(this), "Mardi");
        adapter.addFrag(new TabFragment(this), "Jeudi");
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