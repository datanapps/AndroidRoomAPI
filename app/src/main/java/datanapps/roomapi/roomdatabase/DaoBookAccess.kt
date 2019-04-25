package datanapps.roomapi.roomdatabase


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoBookAccess {

    @Insert
    fun insertTask(note: Book): Long?


    /* @Query("SELECT * FROM Book ORDER BY created_at desc")
    LiveData<List<Book>> fetchAllTasks();*/

    @Query("SELECT * FROM Book")
    fun fetchAllTasks(): LiveData<List<Book>>


    @Query("SELECT * FROM Book WHERE id =:taskId")
    fun getTask(taskId: Int): LiveData<Book>


    @Update
    fun updateTask(note: Book)


    @Delete
    fun deleteTask(note: Book)
}