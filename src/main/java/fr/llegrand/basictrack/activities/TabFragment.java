package fr.llegrand.basictrack.activities;

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
import java.util.Collections;
import java.util.List;

import fr.llegrand.basictrack.R;
import fr.llegrand.basictrack.adapters.RecyclerViewAdapter;
import fr.llegrand.basictrack.models.Exercice;

public class TabFragment extends Fragment {
    private Activity act;
    private List<Exercice> exercices;
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;

    public TabFragment() {
    }

    @SuppressLint("ValidFragment")
    public TabFragment(Activity act, List<Exercice> exercices) {
        this.act = act;
        this.exercices = new ArrayList<>(exercices);
        Collections.sort(this.exercices);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        adapter = new RecyclerViewAdapter(exercices, view.getContext());
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}