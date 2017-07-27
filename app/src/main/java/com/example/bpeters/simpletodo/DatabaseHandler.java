package com.example.bpeters.simpletodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by bpeters on 2017-07-26.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todoItemManager";

    // Todo items table name
    private static final String TABLE_ITEMS = "todoItems";

    // Todo items Columns names
    private static final String KEY_ID = "id";
    private  static final String KEY_ITEM = "item";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM + " TEXT" + ")";

        db.execSQL(CREATE_TODO_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        // Create tables again
        onCreate(db);
    }

    // Add new item
    public void addItem(TodoItem item){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ITEM, item.getItem());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    // Get single item
    public TodoItem getItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS, new String[] {KEY_ID, KEY_ITEM}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TodoItem item = new TodoItem(Integer.valueOf(cursor.getString(0)), cursor.getString(1));

        return item;
    }

    // Getting all the items
    public ArrayList<TodoItem> getAllItems() {
        ArrayList<TodoItem> itemList = new ArrayList<TodoItem>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                TodoItem todoItem = new TodoItem();
                todoItem.setID((Integer.valueOf(cursor.getString(0))));
                todoItem.setItem(cursor.getString(1));
                // Adding item to list
                itemList.add(todoItem);
            } while (cursor.moveToNext());
        }
        return itemList;
    }
}
