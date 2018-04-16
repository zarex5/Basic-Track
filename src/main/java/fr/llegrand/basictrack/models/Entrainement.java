package fr.llegrand.basictrack.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Entrainement implements Serializable {
    private Exercice exercice;
    private List<Serie> series;
    private Date date;

    public Entrainement(Exercice exercice, List<Serie> series) {
        this.exercice = exercice;
        this.series = series;
        this.date = new Date();
    }

    public Exercice getExercice() {
        return exercice;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Entrainement{exercice=" + exercice + ", series=" + series + ", date=" + date + '}';
    }
}