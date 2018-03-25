package fr.llegrand.basictrack.models;

public class Exercice {
    String nom;
    int photoId;
    int series;
    int repetitions;
    double poids;

    public Exercice(String nom, int photoId, int series, int repetitions, double poids) {
        this.nom = nom;
        this.photoId = photoId;
        this.series = series;
        this.repetitions = repetitions;
        this.poids = poids;
    }

    public String getNom() {
        return nom;
    }

    public int getPhotoId() {
        return photoId;
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
}
