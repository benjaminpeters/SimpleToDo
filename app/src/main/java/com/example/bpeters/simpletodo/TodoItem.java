package com.example.bpeters.simpletodo;

/**
 * Created by bpeters on 2017-07-27.
 */

public class TodoItem {

    // private variables
    int _id;
    String _item;

    // Empty constructor
    public TodoItem(){

    }

    // Constructor
    public TodoItem(int id, String item){
        this._id = id;
        this._item = item;
    }

    // Constructor
    public TodoItem(String item){
        this._item = item;
    }

    // Getting ID
    public int getID(){
        return this._id;
    }

    // Setting ID
    public void setID(int id){
        this._id = id;
    }

    // Getting item
    public String getItem(){
        return this._item;
    }

    // Setting item
    public void setItem(String item){
        this._item = item;
    }
}
