package com.example.bpeters.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText mlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mlEditText = (EditText) findViewById(R.id.mlEditText);

        String value = getIntent().getStringExtra("value");
        String position = getIntent().getStringExtra("position");

        mlEditText.setText(value);
    }
}
