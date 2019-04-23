package datanapps.roomapi.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book {
    @PrimaryKey(autoGenerate = true)
    private val id: Int = 0
    private val bookTitle: String? = null
    var authorName: String? = null
    private val bookImage: String? = null
    private val publishedYear: Int = 0
}