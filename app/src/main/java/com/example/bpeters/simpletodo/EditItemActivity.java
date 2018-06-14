package com.example.bpeters.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.bpeters.simpletodo.database.entities.ToDoItem;

public class EditItemActivity extends AppCompatActivity {

    EditText mlEditText;
    String position;
    ToDoItem todo_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mlEditText = (EditText) findViewById(R.id.mlEditText);

        todo_item = (ToDoItem)getIntent().getSerializableExtra("value");

        String value = todo_item.getToDoItem();
        position = getIntent().getStringExtra("position");

        mlEditText.setText(value);

        // Set cursor to the end of the textview
        mlEditText.setSelection(mlEditText.getText().length());
    }

    public void onSave(View v){
        mlEditText = (EditText) findViewById(R.id.mlEditText);

        todo_item.setToDoItem(String.valueOf(mlEditText.getText()));

        Intent data = new Intent();
        data.putExtra("value", todo_item);
        data.putExtra("pos", position);
        setResult(RESULT_OK, data);

        finish();
    }
}

