package fr.llegrand.basictrack.activities.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fr.llegrand.basictrack.R;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final ImageView byiNo = (ImageView) findViewById(R.id.byiNo);
        byiNo.setVisibility(View.INVISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });

        LinearLayout githubLink = (LinearLayout) findViewById(R.id.githubLink);
        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.link_github)));
                startActivity(i);
            }
        });

        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(byiNo.getVisibility() == View.INVISIBLE)
                    byiNo.setVisibility(View.VISIBLE);
                else
                    byiNo.setVisibility(View.INVISIBLE);
            }
        });
    }
}