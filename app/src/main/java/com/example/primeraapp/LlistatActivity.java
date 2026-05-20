package com.example.primeraapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LlistatActivity extends AppCompatActivity implements
        TaskAdapter.OnItemClickListener {

    RecyclerView llistat;
    EditText editTextAfegit;

    private ArrayList<Task> dataSet;

    RecyclerView.LayoutManager layoutManager;

    TaskAdapter taskAdapter;

    FirstFragment listFragment;

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

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FirstFragment firstFragment = FirstFragment.newInstance();
        ft.add(R.id.frame, firstFragment);
        ft.commit();

        editTextAfegit = findViewById(R.id.textAfgir);
        layoutManager = new LinearLayoutManager(this);
        //llistat.setLayoutManager(layoutManager); //vertical


        if (savedInstanceState == null) {
            dataSet = new ArrayList<>();
            dataSet.add(new Task("Task 1"));
            dataSet.add(new Task("Task 2"));
        }

        llistat = findViewById(R.id.rLlistat);
        llistat.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter(dataSet);
        taskAdapter.setListener(this);
        llistat.setAdapter(taskAdapter);

        if (savedInstanceState == null) {
            //createDummyContent();
            taskAdapter = new TaskAdapter(dataSet);
            taskAdapter.setListener(this);
            llistat.setAdapter(taskAdapter);
        }
    }

    @Override
    public void onClick(View view, int position, String task) {
        //Desde aqui crear Intent + startActivity
        //Intent intent = new Intent(getApplicationContext(), );
        Log.v("PRUEBAS",position+"");
        showDetail(task);
    }

    public void addClicked(View view) {
        String text = editTextAfegit.getText().toString();
        dataSet.add(new Task(text));
        taskAdapter.notifyDataSetChanged();
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("dataSet", dataSet);
        outState.putString("todoText", editTextAfegit.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dataSet = savedInstanceState.getParcelableArrayList("dataSet");
        editTextAfegit.setText(savedInstanceState.getString("todoText"));
        taskAdapter = new TaskAdapter(dataSet);
        llistat.setAdapter(taskAdapter);
    }

//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        boolean orientationLand = (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE?true:false);
//
//        if(orientationLand) {
//            layoutManager = new GridLayoutManager(this, 2); // horitzontal en dos columnes
//        } else {
//            layoutManager = new LinearLayoutManager(this); // vertical en llista
//        }
//        llistat.setLayoutManager(layoutManager);
//    }

    public void addTaskClick(View view) {
        Log.v("Laura", "Hello");
    }

    public void changeClicked(View view) {
        SecondFragment secondFragment = SecondFragment.newInstance("hola");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame, secondFragment);

        listFragment = (FirstFragment) fm.findFragmentById(R.id.frame);
        ft.replace(R.id.frame, secondFragment);
        ft.commit();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

        boolean change = true;
        FragmentManager fm = getSupportFragmentManager();
        Fragment retainedFragment = fm.findFragmentById(R.id.frame);
        //Si estamos en el detalle, no cambiar la vista, solo en el listado:
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (retainedFragment instanceof SecondFragment) {
                super.onConfigurationChanged(null);
                change = false;
            }
        }
        if (change)
            super.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.item1) {
            Log.v("Laura", "add menu clicked");

        }

        return super.onOptionsItemSelected(item);
    }

    public void showDetail(String task) {
        SecondFragment detailFragment = SecondFragment.newInstance(task);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

      /*  int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ft.replace(R.id.detail_fragment, detailFragment );

        } else {*/
        listFragment = (FirstFragment) fm.findFragmentById(R.id.frame);
        ft.replace(R.id.frame, detailFragment);
        //  }
        ft.commit();
    }

    public void showList() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, listFragment);
        ft.commit();
    }

}