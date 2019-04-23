package datanapps.roomapi.roomdatabase;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoBookAccess {

    @Insert
    Long insertTask(Book note);


   /* @Query("SELECT * FROM Book ORDER BY created_at desc")
    LiveData<List<Book>> fetchAllTasks();*/

    @Query("SELECT * FROM Book")
    LiveData<List<Book>> fetchAllTasks();


    @Query("SELECT * FROM Book WHERE id =:taskId")
    LiveData<Book> getTask(int taskId);


    @Update
    void updateTask(Book note);


    @Delete
    void deleteTask(Book note);
}