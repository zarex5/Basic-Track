package fr.llegrand.basictrack.models;

import java.io.Serializable;

public class Serie implements Serializable {
    private int repetitions;
    private double poids;

    public Serie(int repetitions, double poids) {
        this.repetitions = repetitions;
        this.poids = poids;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getPoids() {
        return poids;
    }

    @Override
    public String toString() {
        return "Serie{repetitions=" + repetitions + ", poids=" + poids + '}';
    }
}