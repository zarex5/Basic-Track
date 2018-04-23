package fr.llegrand.basictrack.activities.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import fr.llegrand.basictrack.R;
import fr.llegrand.basictrack.models.Jour;

public class EditDaysActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdays);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_editdays);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Log.i("NAV", "Retour MainActivity");
            }
        });

        /*Button btn_reinit = (Button) findViewById(R.id.button_reinit);
        btn_reinit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("jours", MODE_PRIVATE).edit();
                editor.putString("jours", null);
                editor.apply();

                Toast toast = Toast.makeText(getApplicationContext(), "SharedPerfs réinitialisé", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button btn_add = (Button) findViewById(R.id.button_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Gson gson = new GsonBuilder().serializeNulls().create();
                SharedPreferences prefs = getSharedPreferences("jours", MODE_PRIVATE);
                String restoredText = prefs.getString("jours", null);
                ArrayList<Jour> jours = gson.fromJson(restoredText, new TypeToken<ArrayList<Jour>>(){}.getType());

                String s_name = ((EditText) findViewById(R.id.input_days)).getText().toString();
                int s_pos = Integer.valueOf(((EditText) findViewById(R.id.input_pos)).getText().toString());
                jours.add(new Jour(4, s_name, s_pos));

                SharedPreferences.Editor editor = getSharedPreferences("jours", MODE_PRIVATE).edit();
                editor.putString("jours", gson.toJson(jours).toString());
                editor.apply();

                Toast toast = Toast.makeText(getApplicationContext(), "Jour ajouté", Toast.LENGTH_SHORT);
                toast.show();
            }
        });*/
    }
}