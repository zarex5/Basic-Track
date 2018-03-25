package fr.llegrand.basictrack;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ExerciceViewHolder>{

    List<Exercice> exercices;

    RVAdapter(List<Exercice> exercices){
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
        exerciceViewHolder.nomExercice.setText(exercices.get(i).nom);
        exerciceViewHolder.photoExercice.setImageResource(exercices.get(i).photoId);
        exerciceViewHolder.infosExercice.setText("Séries: " + exercices.get(i).series + " | Répetitions: " + exercices.get(i).repetitions + " | Poids: " + exercices.get(i).poids);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}