package fr.llegrand.basictrack.models;

import android.support.annotation.NonNull;

public class Jour implements Comparable<Jour>{
    private int id;
    private String nom;
    private int position;

    public Jour(String nom) {
        this.nom = nom;
    }

    public Jour(int id, String nom, int position) {
        this.id = id;
        this.nom = nom;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(@NonNull Jour jour) {
        return (this.position - jour.getPosition());
    }

    @Override
    public String toString() {
        return "Jour{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}