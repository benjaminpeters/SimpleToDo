package com.example.bpeters.simpletodo;

import com.example.bpeters.simpletodo.database.entities.ToDoItem;

import org.junit.After;
import org.junit.Before;

public class MainActivityTest {


    ToDoItem testitem = new ToDoItem();

    @Before
    public void setUp() throws Exception {
        this.testitem.setToDoItem("cut grass");
    }

    @After
    public void tearDown() throws Exception {
    }
}