package fr.llegrand.basictrack;

public class Exercice {
    String nom;
    int photoId;
    int series;
    int repetitions;
    double poids;


    Exercice(String nom, int photoId, int series, int repetitions, double poids) {
        this.nom = nom;
        this.photoId = photoId;
        this.series = series;
        this.repetitions = repetitions;
        this.poids = poids;
    }
}
