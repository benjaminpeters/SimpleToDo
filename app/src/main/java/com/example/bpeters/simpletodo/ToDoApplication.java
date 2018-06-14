package com.example.bpeters.simpletodo;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.bpeters.simpletodo.database.ToDoDatabase;

public class ToDoApplication extends Application {

    private static ToDoDatabase sToDoDatabase;

    @Override
    public void onCreate(){
        super.onCreate();
        sToDoDatabase = Room.databaseBuilder(this, ToDoDatabase.class, ToDoDatabase.class.getSimpleName()+"1").fallbackToDestructiveMigration().build();
    }

    public static ToDoDatabase getDatabase(){
        return sToDoDatabase;
    }
}
