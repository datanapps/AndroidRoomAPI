package datanapps.roomapi.roomdatabase

import android.content.Context

import androidx.lifecycle.LiveData
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookRepository(context: Context) {

    private val DB_NAME = "db_task"
    private val appDatabase: AppDB

    init {
        appDatabase = Room.databaseBuilder(context, AppDB::class.java, DB_NAME).build()
    }

    fun insertTask(note: Book) {

        GlobalScope.launch {
            appDatabase.daoBookAccess().insertTask(note)
        }
    }



    fun updateBook(book: Book) {
        GlobalScope.launch {
            appDatabase.daoBookAccess().updateTask(book)
        }
    }


    fun deleteBookById(id: Int) {
        val book = getBookById(id)
        if (book != null) {
            deleteBook(book.value!!)
        }
    }

    fun deleteBook(note: Book) {
        GlobalScope.launch {
            appDatabase.daoBookAccess().deleteTask(note)
        }
    }

    fun getBookById(id: Int): LiveData<Book>? {
        return appDatabase.daoBookAccess().getTask(id)
    }


    val getAllBookList: LiveData<List<Book>>
        get() = appDatabase.daoBookAccess().fetchAllTasks()

}
