package com.example.notespace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    Button cancel;
    Button save;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Note");
        setContentView(R.layout.activity_edit_note);

        title = findViewById(R.id.editTextTitleUpdate);
        description = findViewById(R.id.editTextDescriptionUpdate);
        cancel = findViewById(R.id.buttonCancelUpdate);
        save = findViewById(R.id.buttonSaveUpdate);

        getData();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditNoteActivity.this, "Changes Not Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
                Toast.makeText(EditNoteActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateNote(){

        String titleLast = title.getText().toString();
        String descriptionLast = description.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("titleLast",titleLast);
        intent.putExtra("descriptionLast",descriptionLast);
        if (id != -1){
            intent.putExtra("id",id);
            setResult(RESULT_OK,intent);
        }
    }
    public void getData(){
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        String noteTitle = intent.getStringExtra("title");
        String noteDescription = intent.getStringExtra("description");

        title.setText(noteTitle);
        description.setText(noteDescription);
    }
}
