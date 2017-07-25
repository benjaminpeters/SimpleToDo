package com.example.bpeters.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText mlEditText;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mlEditText = (EditText) findViewById(R.id.mlEditText);

        String value = getIntent().getStringExtra("value");
        position = getIntent().getStringExtra("position");

        mlEditText.setText(value);

        // Set cursor to the end of the textview
        mlEditText.setSelection(mlEditText.getText().length());
    }

    public void onSave(View v){
        mlEditText = (EditText) findViewById(R.id.mlEditText);

        Intent data = new Intent();
        data.putExtra("value", String.valueOf(mlEditText.getText()));
        data.putExtra("pos", position);
        setResult(RESULT_OK, data);
        finish();
    }
}

