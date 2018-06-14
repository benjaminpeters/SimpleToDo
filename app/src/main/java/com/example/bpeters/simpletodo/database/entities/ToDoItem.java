package com.example.bpeters.simpletodo.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by bpeters on 2017-07-27.
 */


@Entity
public class ToDoItem implements Serializable {

    @PrimaryKey
    @NonNull
    private String toDoItemID;
    private String toDoItem;

    public ToDoItem() {

    }

    public String getToDoItemID() { return toDoItemID; }
    public void setToDoItemID(String toDoItemID) { this.toDoItemID = toDoItemID; }
    public String getToDoItem() { return toDoItem; }
    public void setToDoItem( String toDoItem ) { this.toDoItem = toDoItem; }

    @Override
    public String toString() {
        return this.toDoItem;
    }

}


