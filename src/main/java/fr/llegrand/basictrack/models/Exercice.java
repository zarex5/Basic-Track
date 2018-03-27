package fr.llegrand.basictrack.models;

public class Exercice {
    private int id;
    private String nom;
    private int photoId;
    private int series;
    private int repetitions;
    private double poids;
    private int position;
    private int id_jour;

    public Exercice(int id, String nom, int photoId, int series, int repetitions, double poids, int position, int id_jour) {
        this.id = id;
        this.nom = nom;
        this.photoId = photoId;
        this.series = series;
        this.repetitions = repetitions;
        this.poids = poids;
        this.position = position;
        this.id_jour = id_jour;
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
                ", id_jour=" + id_jour +
                '}';
    }
}
