package fr.llegrand.basictrack;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
}
