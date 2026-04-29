package com.example.primeraapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LlistatActivity extends AppCompatActivity implements
        TaskAdapter.OnItemClickListener {

    RecyclerView llistat;
    private ArrayList<Task> dataSet;

    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_llistat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataSet = new ArrayList<>();
        dataSet.add(new Task("Task 1"));
        dataSet.add(new Task("Task 2"));

        llistat = findViewById(R.id.rLlistat);
        llistat.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter(dataSet);
        llistat.setAdapter(taskAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        Log.v("PRUEBAS",position+"");
    }
}