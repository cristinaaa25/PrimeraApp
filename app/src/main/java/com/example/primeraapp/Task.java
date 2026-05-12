package com.example.primeraapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Random;

public class Task implements Parcelable {
    private int id;
    private String task;

    public Task(String task) {
        this.id = new Random().nextInt(9999);
        this.task = task;
    }

    public Task(Parcel in)
    {
        super();
        readFromParcel(in);
    }

    public static final Creator<Task> CREATOR =
            new Creator<Task>(){

                @Override
                public Task createFromParcel(Parcel parcel) {
                    return new Task(parcel);
                }

                @Override
                public Task[] newArray(int i) {
                    return new Task[i];
                }
            };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(task);
    }

    public void readFromParcel (Parcel in)
    {
        id = in.readInt();
        task = in.readString();
    }
}