package datanapps.roomapi

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import datanapps.roomapi.roomdatabase.Book
import datanapps.roomapi.roomdatabase.BookRepository


class MainActivity : AppCompatActivity() {

    var booksAdapter = BooksAdapter(this@MainActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set tool bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // set floating action
        val fab = findViewById<FloatingActionButton>(R.id.fab_bar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show()
            addBook()

        }

        // set recycle view
        setRecycleViewList()

    }


    private fun setRecycleViewList() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view_book)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = booksAdapter
        booksAdapter.deleteClickListener(View.OnClickListener {
            val noteRepository = BookRepository(applicationContext)

            var book = it.getTag() 
            if(book is Book) {
                noteRepository.deleteBook(book)
            }

        })
    }


    override fun onResume() {
        super.onResume()
        getBookListFromRecordFromDB()
    }



    private fun getBookListFromRecordFromDB() {
        val noteRepository = BookRepository(applicationContext)
        noteRepository.getAllBookList.observe(this, androidx.lifecycle.Observer {
            booksAdapter.updateBookList(it)
        })

    }


    private fun addBook(){

        val noteRepository = BookRepository(applicationContext)

        // Start a coroutine

            var book = Book()
            book.bookTitle = "History-books-for-Kids"
            book.authorName = "unknown"
            book.publishedYear = 2000
            noteRepository.insertTask(book)

            getBookListFromRecordFromDB()


    }

}
