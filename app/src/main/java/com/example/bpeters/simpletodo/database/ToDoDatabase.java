package com.example.bpeters.simpletodo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.bpeters.simpletodo.database.entities.ToDoItem;

@Database(entities = ToDoItem.class, version = 1, exportSchema = false)
public abstract class ToDoDatabase extends RoomDatabase{
    public abstract ToDoDao homePageDao();
}
