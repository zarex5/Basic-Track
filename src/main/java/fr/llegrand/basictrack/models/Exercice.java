package fr.llegrand.basictrack.models;

import java.io.Serializable;

import fr.llegrand.basictrack.R;

public class Exercice implements Serializable {
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
            case "PECTORAUX": this.photoId = R.drawable.pectoraux; break;
            case "BICEPS": this.photoId = R.drawable.biceps; break;
            case "FESSIERS": this.photoId = R.drawable.fessiers; break;
            case "DORSAUX": this.photoId = R.drawable.dorsaux; break;
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
    public String toString() {
        return "Exercice{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", muscle='" + muscle + '\'' +
                ", id_jour=" + id_jour +
                '}';
    }
}
