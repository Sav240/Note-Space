package com.example.notespace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    Button cancel;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("New Note");
        setContentView(R.layout.activity_new_note);

        title = findViewById(R.id.editTextTitle);
        description = findViewById(R.id.editTextDescription);
        cancel = findViewById(R.id.buttonCancel);
        save = findViewById(R.id.buttonSave);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewNoteActivity.this, "Changes Not Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                finish();
            }
        });
    }
    public void saveNote(){
        String noteTitle = title.getText().toString();
        String noteDescription = description.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("noteTitle", noteTitle);
        intent.putExtra("noteDescription", noteDescription);
        setResult(RESULT_OK, intent);
        Toast.makeText(NewNoteActivity.this,"Note Saved",Toast.LENGTH_SHORT).show();
    }
}