package com.example.notespace;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

public class NoteRepository {

    private NoteDAO noteDAO;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        notes = noteDAO.getAllNotes();
    }
   public void insert(Note note){
        new InsertNoteAsyncTask(noteDAO).execute(note);
   }
   public void update(Note note){
       new UpdateNoteAsyncTask(noteDAO).execute(note);
   }
   public void delete(Note note){
       new DeleteNoteAsyncTask(noteDAO).execute(note);
   }
   public LiveData<List<Note>> getAllNotes(){
        return notes;
   }

   //Async Insert Task
   private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void> {
       private NoteDAO noteDAO;

       private InsertNoteAsyncTask(NoteDAO noteDAO){
           this.noteDAO = noteDAO;
       }
        @Override
       protected Void doInBackground(Note... notes) {
           noteDAO.Insert(notes[0]);
           return null;
       }
   }
   //Async Update Task
    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDAO noteDAO;

        private UpdateNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.Update(notes[0]);
            return null;
        }
    }
    //Async Delete Task
    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDAO noteDAO;

        private DeleteNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }
}
