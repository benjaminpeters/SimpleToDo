package com.example.bpeters.simpletodo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bpeters.simpletodo.database.ToDoDatabase;
import com.example.bpeters.simpletodo.database.entities.ToDoItem;
import com.example.bpeters.simpletodo.utilities.ThreadManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ToDoItem> todoItems;
    ArrayList<String> todoStrings;
    ArrayAdapter<ToDoItem> aToDoAdapter;
    ListView lvitems;
    EditText etEditText;
    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;

    private static final String DATABASE_NAME = "todo_db";
    private ToDoDatabase toDoDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        toDoDatabase = Room.databaseBuilder(getApplicationContext(),
                ToDoDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        populateArrayItems();
        lvitems = (ListView) findViewById(R.id.lvitems);
        lvitems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.eteditText);

        lvitems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id){

                final ToDoItem clickedItem = (ToDoItem) parent.getItemAtPosition(position);

                // update db on background thread
                ThreadManager.runOnBackgroundThread(new Runnable() {
                        @Override
                        public void run() {
                            toDoDatabase.homePageDao().deleteToDoItem(clickedItem);
                        }
                    }
                );

                // update the UI on main thread
                ThreadManager.runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        todoItems.remove(position);
                        aToDoAdapter.notifyDataSetChanged();
                        }
                    }
                );

                return true;
            }
        });

        lvitems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                ToDoItem clickedItem = (ToDoItem) parent.getItemAtPosition(position);
                launchEditActivity(clickedItem, String.valueOf(position));
            }
        });
    }

    public void populateArrayItems(){
        // pull data from db on background thread
//        ThreadManager.runOnBackgroundThread(new Runnable() {
//            @Override
//            public void run() {
                todoItems = toDoDatabase.homePageDao().getAll();
//                }
//            }
//        );

        aToDoAdapter = new ArrayAdapter<ToDoItem>(this, android.R.layout.simple_list_item_1,
                todoItems);
    }

    public void launchEditActivity(ToDoItem val, String pos) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("value", val);
        i.putExtra("position", pos);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onAddItem(View view) {
        final String todoText = etEditText.getText().toString();
        if (!todoText.equals("")) {
//            final String pos = Integer.toString(todoItems.size() + 2);
            final ToDoItem item = new ToDoItem();
//            item.setToDoItemID(pos);
            item.setToDoItem(todoText);

            Log.d("mainactivity add item", todoItems.toString());

            // update the UI on main thread
            ThreadManager.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            aToDoAdapter.add(item);
                            etEditText.setText("");
                        }
                    }
            );

            // update db on background thread
            ThreadManager.runOnBackgroundThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            toDoDatabase.homePageDao().insertToDoItem(item);
                        }
                    }
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            final ToDoItem item;
            item = (ToDoItem)data.getSerializableExtra("value");
            int pos = Integer.parseInt(data.getExtras().getString("pos"));
            todoItems.set(pos, item);

            // update the UI on main thread
            ThreadManager.runOnUiThread( new Runnable() {
                @Override
                public void run() {
                    aToDoAdapter.notifyDataSetChanged();
                    }
                }
            );
            // update db on background thread
            ThreadManager.runOnBackgroundThread(new Runnable() {
                @Override
                public void run() {
                    toDoDatabase.homePageDao().updateToDoItem (item);
                    }
                }
            );
        }
    }
}
