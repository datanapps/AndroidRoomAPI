package datanapps.roomapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import datanapps.roomapi.roomdatabase.Book

class BooksAdapter(private val context: Context) : RecyclerView.Adapter<BooksAdapter.UserViewHolder>() {


    private var moviesList: List<Book>
    private lateinit var deleteClickListener: View.OnClickListener
    init {
        moviesList = arrayListOf<Book>()
    }


    fun updateBookList(moviesList: List<Book>){
        this.moviesList = moviesList
    }

    fun deleteClickListener(clickListener: View.OnClickListener){
       this.deleteClickListener = clickListener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_book_list, parent, false)

        return UserViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val book = moviesList[position]
        holder.bookName.text = book.bookTitle
        holder.bookAuthor.text = book.authorName
        holder.publishedYear.text = book.publishedYear.toString()
        holder.imageDelete.setTag(book)
        holder.imageDelete.setOnClickListener(deleteClickListener);


    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bookName: TextView
        var bookAuthor: TextView
        var imageDelete: ImageView
        var publishedYear: TextView


        init {
            bookName = view.findViewById(R.id.layout_book_list_title)
            bookAuthor = view.findViewById(R.id.layout_book_list_author)
            imageDelete = view.findViewById(R.id.layout_book_list_delete)
            publishedYear = view.findViewById(R.id.layout_book_list_year)

        }
    }



}
