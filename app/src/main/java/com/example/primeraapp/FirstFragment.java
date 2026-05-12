package com.example.primeraapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView llistat;
    EditText editTextAfegit;
    private ArrayList<Task> dataSet;

    RecyclerView.LayoutManager layoutManager;

    TaskAdapter taskAdapter;

    Button btnAfegir;



    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        dataSet = new ArrayList<Task>();
        dataSet.add(new Task("Task 1"));
        llistat = view.findViewById(R.id.rLlistat2);
        taskAdapter = new TaskAdapter(dataSet);
        layoutManager = new LinearLayoutManager(getContext());
        llistat.setLayoutManager(layoutManager);
        llistat.setAdapter(taskAdapter);
        btnAfegir = view.findViewById(R.id.button2);
        btnAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // El que volem fer al click, agefir fila al llistat
            }
        });

        return view;

    }
}