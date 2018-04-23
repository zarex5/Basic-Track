package fr.llegrand.basictrack.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Entrainement implements Serializable {
    private Exercice exercice;
    private List<Serie> series;
    private String commentaires;
    private boolean seul;
    private int position;
    private Date date;

    public Entrainement(Exercice exercice, List<Serie> series, String commentaires, boolean seul, int position, Date date) {
        this.exercice = exercice;
        this.series = series;
        this.commentaires = commentaires;
        this.seul = seul;
        this.position = position;
        this.date = date;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public boolean isSeul() {
        return seul;
    }

    public int getPosition() {
        return position;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Entrainement{exercice=" + exercice + ", series=" + series + ", commentaires=" + commentaires + ", seul=" + seul + ", position=" + position + ", date=" + date + '}';
    }
}