package fr.llegrand.basictrack.util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.llegrand.basictrack.activities.MainActivity;
import fr.llegrand.basictrack.models.Entrainement;
import fr.llegrand.basictrack.models.Exercice;
import fr.llegrand.basictrack.models.Serie;

public class Reader {
    private final static String directoryPath = Environment.getExternalStorageDirectory() + "/Basic-Track/";
    private final static String entrainementsFile = "entrainements.dat";

    public static ArrayList<Entrainement> getEntrainements() {
        checkDirectory();

        ArrayList<Entrainement> entrainements = null;
        File directory = new File(directoryPath);
        File file = new File(directory, entrainementsFile);

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fis);
            entrainements = (ArrayList<Entrainement>) is.readObject();
            is.close();
            fis.close();
        } catch (IOException e) {
            if (ContextCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.w("IO", "Fichier " + entrainementsFile + " inexistant");
            } else {
                Log.e("IO", "Permission d'écrire non accordée");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (entrainements == null) entrainements = new ArrayList<Entrainement>();
        return entrainements;
    }

    public static void setEntrainements(ArrayList<Entrainement> entrainements) throws Exception {
        checkDirectory();

        File directory = new File(directoryPath);
        File file = new File(directory, entrainementsFile);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(entrainements);
            os.close();
            fos.close();
        } catch (IOException e) {
            if (ContextCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e("IO", "Erreur d'écriture " + entrainementsFile);
            } else {
                Log.e("IO", "Permission d'écrire non accordée");
                throw new Exception();
            }
        }
    }

    public static String exportToCSV(ArrayList<Entrainement> entrainements) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String exportsFile = "export-" + sdf.format(new Date())+ ".csv";

        checkDirectory();

        File directory = new File(directoryPath);
        File file = new File(directory, exportsFile);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.ISO_8859_1);
            osw.write("idExercice;nomExercice;muscleExercice;commentaires;seul;position;date;repsSerie1;kgsSerie1;repsSerie2;kgsSerie2;repsSerie3;kgsSerie3;repsSerie4;kgsSerie4;repsSerie5;kgsSerie5\n");
            for(Entrainement e : entrainements) {
                Exercice ex = e.getExercice();
                String string = ex.getId() + ";" + ex.getNom() + ";" + ex.getMuscle() + ";" + e.getCommentaires() + ";" + e.isSeul()  + ";" + e.getPosition() + ";" + e.getDate();
                for(Serie s : e.getSeries()){
                    string += ";" + s.getRepetitions() + ";" + s.getPoids();
                }
                osw.write(string + "\n");
            }
            osw.close();
            fos.close();
        } catch (IOException e) {
            if (ContextCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e("IO", "Erreur d'écriture " + exportsFile);
            } else {
                Log.e("IO", "Permission d'écrire non accordée");
                throw new Exception();
            }
        }
        return exportsFile;
    }

    private static void checkDirectory() {
        File directory = new File(directoryPath);
        if (!directory.exists())
            directory.mkdirs();
    }
}