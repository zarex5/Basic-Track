package fr.llegrand.basictrack.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

import fr.llegrand.basictrack.R;

public class Exercice implements Serializable, Comparable<Exercice>{
    private int id;
    private String nom;
    private int photoId;
    private String muscle;
    private int series;
    private int repetitions;
    private double poids;
    private int position;
    private int id_jour;

    public Exercice(int id, String nom, String muscle, int series, int repetitions, double poids, int position, int id_jour) {
        this.id = id;
        this.nom = nom;
        this.muscle = muscle;
        this.series = series;
        this.repetitions = repetitions;
        this.poids = poids;
        this.position = position;
        this.id_jour = id_jour;

        switch(this.muscle){
            case "ABDOS": this.photoId = R.drawable.abdos; break;
            case "ABDUCTEURS": this.photoId = R.drawable.abducteurs; break;
            case "BICEPS": this.photoId = R.drawable.biceps; break;
            case "CARDIO": this.photoId = R.drawable.cardio; break;
            case "DORSAUX": this.photoId = R.drawable.dorsaux; break;
            case "EPAULES": this.photoId = R.drawable.epaules; break;
            case "FESSIERS": this.photoId = R.drawable.fessiers; break;
            case "ISCHIOS": this.photoId = R.drawable.ischios; break;
            case "MOLLETS": this.photoId = R.drawable.mollets; break;
            case "PECTORAUX": this.photoId = R.drawable.pectoraux; break;
            case "TRAPEZES": this.photoId = R.drawable.trapezes; break;
            case "TRICEPS": this.photoId = R.drawable.triceps; break;
            default: this.photoId = R.drawable.cardio; break;
        }
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getMuscle() {
        return muscle;
    }

    public int getSeries() {
        return series;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getPoids() {
        return poids;
    }

    public int getPosition() {
        return position;
    }

    public int getId_jour() {
        return id_jour;
    }

    @Override
    public int compareTo(@NonNull Exercice exercice) {
        return (this.position - exercice.getPosition());
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", muscle='" + muscle + '\'' +
                ", id_jour=" + id_jour +
                '}';
    }
}