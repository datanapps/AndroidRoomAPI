package datanapps.roomapi.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class BookRepository {

    private String DB_NAME = "db_task";
    private AppDB noteDatabase;


    public BookRepository(Context context) {
        noteDatabase = Room.databaseBuilder(context, AppDB.class, DB_NAME).build();
    }

    public void insertTask(final Book note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().insertTask(note);
                return null;
            }
        }.execute();
    }

    public void updateTask(final Book note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().updateTask(note);
                return null;
            }
        }.execute();
    }

    public void deleteTask(final int id) {
        final LiveData<Book> task = getTask(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    noteDatabase.daoAccess().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteTask(final Book note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().deleteTask(note);
                return null;
            }
        }.execute();
    }

    public LiveData<Book> getTask(int id) {
        return noteDatabase.daoAccess().getTask(id);
    }

    public LiveData<List<Book>> getTasks() {
        return noteDatabase.daoAccess().fetchAllTasks();
    }

}
