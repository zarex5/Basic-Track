package fr.llegrand.basictrack;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    NotificationManagerCompat notificationManager = null;
    NotificationCompat.Builder mBuilder = null;
    int i = 0;

    private View.OnClickListener btnAddListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.i("CLICK", "BOUTON AJOUTER");

            EditText prenom = (EditText) findViewById(R.id.prenom);
            EditText nom = (EditText) findViewById(R.id.nom);
            String person = prenom.getText() + " " + nom.getText();
            Toast toast = Toast.makeText(getApplicationContext(), person + " a bien été ajouté!", Toast.LENGTH_SHORT);
            toast.show();

            i++;
            if(i==3) {
                notificationManager.notify(1, mBuilder.build());
                i = 0;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("INFO", "CONTENU SET");
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.my_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        Toast toast = Toast.makeText(getApplicationContext(), "One", Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                    case 1:
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Two", Toast.LENGTH_SHORT);
                        toast2.show();
                        break;
                    case 2:
                        Toast toast3 = Toast.makeText(getApplicationContext(), "Three", Toast.LENGTH_SHORT);
                        toast3.show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        Button button = (Button)findViewById(R.id.ajouter);
        button.setOnClickListener(btnAddListener);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        mBuilder = new NotificationCompat.Builder(this, "1")
                .setContentTitle("Attention")
                .setContentText("Merci de ne pas spammer le bouton")
                .setSmallIcon(R.drawable.ic_smallicon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManager = NotificationManagerCompat.from(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummyFragment(), "CAT");
        adapter.addFrag(new DummyFragment(), "DOG");
        adapter.addFrag(new DummyFragment(), "MOUSE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText nom = (EditText) findViewById(R.id.nom);
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i("DEBUG", "SETTINGS CLICKED");
                nom.setText("");
                return true;

            case R.id.action_plus:
                Log.i("DEBUG", "PLUS CLICKED");
                nom.setText("Legrand");
                return true;

            default:
                Log.i("DEBUG", "??? CLICKED");
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
