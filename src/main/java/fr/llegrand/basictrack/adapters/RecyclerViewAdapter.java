package fr.llegrand.basictrack.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.llegrand.basictrack.models.Exercice;
import fr.llegrand.basictrack.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExerciceViewHolder>{

    List<Exercice> exercices;

    public RecyclerViewAdapter(List<Exercice> exercices){
        this.exercices = exercices;
    }

    public static class ExerciceViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nomExercice;
        ImageView photoExercice;
        TextView infosExercice;

        ExerciceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nomExercice = (TextView)itemView.findViewById(R.id.nomExercice);
            photoExercice = (ImageView)itemView.findViewById(R.id.photoExercice);
            infosExercice = (TextView)itemView.findViewById(R.id.infosExercice);
        }
    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }

    @Override
    public ExerciceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ExerciceViewHolder evh = new ExerciceViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExerciceViewHolder exerciceViewHolder, int i) {
        exerciceViewHolder.nomExercice.setText(exercices.get(i).getNom());
        exerciceViewHolder.photoExercice.setImageResource(exercices.get(i).getPhotoId());
        exerciceViewHolder.infosExercice.setText("Séries: " + exercices.get(i).getSeries() + " | Répetitions: " + exercices.get(i).getRepetitions() + " | Poids: " + exercices.get(i).getPoids());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}