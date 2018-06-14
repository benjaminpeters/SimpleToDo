package com.example.bpeters.simpletodo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.bpeters.simpletodo.database.entities.ToDoItem;

import java.util.List;

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM ToDoItem")
    List<ToDoItem> getAll();

    @Query("SELECT * FROM ToDoItem WHERE toDoItemID = :toDoItemID")
    ToDoItem fetchOneToDoItemByID(int toDoItemID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertToDoItem(ToDoItem todo_item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMultipleToDoItems( List<ToDoItem> toDoItemList);

    @Update
    void updateToDoItem (ToDoItem toDoItem);

    @Delete
    void deleteToDoItem (ToDoItem toDoItem);

}
