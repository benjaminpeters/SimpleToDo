package com.example.bpeters.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TodoItem> todoItems;
    ArrayList<String> todoStrings;
    ArrayAdapter<TodoItem> aToDoAdapter;
    ListView lvitems;
    EditText etEditText;
    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        populateArrayItems();
        lvitems = (ListView) findViewById(R.id.lvitems);
        lvitems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.eteditText);
        lvitems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                todoItems.remove(position);
                aToDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvitems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String clickedItemStr = (String) parent.getItemAtPosition(position);

                launchEditActivity(clickedItemStr, String.valueOf(position));
            }
        });
    }

    public void populateArrayItems(){
        readItems();
        aToDoAdapter = new ArrayAdapter<TodoItem>(this, android.R.layout.simple_list_item_1, todoItems);
    }

    private void readItems(){
//        File fileDir = getFilesDir();
//        File file = new File(fileDir, "todo.txt");
//        try {
//            todoItems = new ArrayList<>(FileUtils.readLines(file));
//        } catch (IOException e){
//
//        }

        todoItems = db.getAllItems();
//        if(todoItems.size() > 0) {
//            for (int ind = 0; ind < todoItems.size(); ind++) {
//                todoStrings.add(ind, todoItems.get(ind).getItem());
//            }
//        }

    }

    private void writeItems(){
//        File fileDir = getFilesDir();
//        File file = new File(fileDir, "todo.txt");
//        try {
//            FileUtils.writeLines(file, todoItems);
//        } catch (IOException e){
//
//        }
        if(todoItems.size() > 0){
            for(int i = 0; i < todoItems.size(); i++){
                TodoItem item = todoItems.get(i);
                db.addItem(item);
            }
        }
    }

    public void launchEditActivity(String val, String pos) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("value", val);
        i.putExtra("position", pos);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onAddItem(View view) {
        TodoItem newItem = new TodoItem(etEditText.getText().toString());
//        aToDoAdapter.add(etEditText.getText().toString());
        aToDoAdapter.add(newItem);
        etEditText.setText("");
        writeItems();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            String name = data.getExtras().getString("value");
//            int pos = Integer.parseInt(data.getExtras().getString("pos"));
//            todoItems.set(pos, name);
//            aToDoAdapter.notifyDataSetChanged();
//            writeItems();
//        }
    }
}
