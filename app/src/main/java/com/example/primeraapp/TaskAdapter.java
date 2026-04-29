package com.example.primeraapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    ArrayList<Task> dataSet;
    public TaskAdapter(ArrayList<Task> data) {
        dataSet = data;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task t = dataSet.get(position);
        holder.tvId.setText(t.getId()+"");
        holder.tvTask.setText(t.getTask()+"");
    }

    @Override
    public int getItemCount() {
        return dataSet.size(); // canvi pel Duque
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        TextView tvId, tvTask;
        OnItemClickListener onItemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.row_id);
            tvTask = itemView.findViewById(R.id.row_desc);
        }


        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    OnItemClickListener listener;
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }
}
