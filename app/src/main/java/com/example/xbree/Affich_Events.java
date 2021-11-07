package com.example.xbree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.xbree.Entities.Evenement;
import com.example.xbree.HomeAdapter.EvenementAdapter;
import com.example.xbree.Utils.database.AppDataBase;

import java.util.ArrayList;
import java.util.List;


public class Affich_Events extends AppCompatActivity {

    private AppDataBase database ;
    private List<Evenement> A = new ArrayList<Evenement>();
    private RecyclerView mRecyclerView;
    private EvenementAdapter adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affich__events);
        mRecyclerView = findViewById(R.id.events);
        //favori = findViewById(R.id.favori);
        database = AppDataBase.getAppDatabase(Affich_Events.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Affich_Events.this));
        A = database.produitDao().getAll();

        adapterList = new EvenementAdapter(Affich_Events.this,A);

        mRecyclerView.setAdapter(adapterList);
    }

}