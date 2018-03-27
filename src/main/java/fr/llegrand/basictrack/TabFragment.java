package fr.llegrand.basictrack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.llegrand.basictrack.adapters.RecyclerViewAdapter;
import fr.llegrand.basictrack.models.Exercice;

public class TabFragment extends Fragment {
    private Activity act;
    private int jour_id;

    public TabFragment() {
    }

    @SuppressLint("ValidFragment")
    public TabFragment(Activity act, int jour_id) {
        this.act = act;
        this.jour_id = jour_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        List<Exercice> exercices = new ArrayList<>();
        exercices.add(new Exercice("Développé couché", R.drawable.pectoraux, 4, 10, 20.0));
        exercices.add(new Exercice("Développé couché incliné", R.drawable.pectoraux, 4, 10, 10.0));
        exercices.add(new Exercice("Curl haltère", R.drawable.biceps, 3, 10, 8.0));

        RecyclerView rv = (RecyclerView)act.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(act.getApplicationContext());
        rv.setLayoutManager(llm);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(exercices, act.getApplicationContext());
        rv.setAdapter(adapter);
    }
}