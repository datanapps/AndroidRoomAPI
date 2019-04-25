package datanapps.roomapi

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import datanapps.roomapi.roomdatabase.Book
import datanapps.roomapi.roomdatabase.BookRepository
import kotlinx.android.synthetic.main.activity_main.*


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

            var book = it.getTag()
            if (book is Book) {
                deleteAlertDialog(book)


            }

        })
    }


    override fun onResume() {
        super.onResume()
        getBookListFromRecordFromDB()
    }


    private fun getBookListFromRecordFromDB() {
        Handler().postDelayed({
            val noteRepository = BookRepository(applicationContext)
            noteRepository.getAllBookList.observe(this, androidx.lifecycle.Observer {
                booksAdapter.updateBookList(it)
            })
        }, 1500)


    }


    private fun addBook() {
        val noteRepository = BookRepository(applicationContext)
        // Start a coroutine
        var book = Book()
        book.bookTitle = "Book Name"+(booksAdapter.itemCount+1)
        book.authorName = "Author "+(booksAdapter.itemCount+1)
        book.publishedYear = 2000 +(booksAdapter.itemCount+1)
        noteRepository.insertTask(book)
        getBookListFromRecordFromDB()
    }


    private fun deleteBook(book: Book){
        val noteRepository = BookRepository(applicationContext)
        noteRepository.deleteBook(book)
        getBookListFromRecordFromDB()
    }

    fun deleteAlertDialog(book: Book) {
        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle("Delete Book")

        // Display a message on alert dialog
        builder.setMessage("Are you want to delete this book")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialog, which ->
            // Do something when user press the positive button
            Snackbar.make(root_layout, "Books Deleted", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            deleteBook(book);
        }

        // Display a negative button on alert dialog
        builder.setNegativeButton("No") { dialog, which ->

        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

}
