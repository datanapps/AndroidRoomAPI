package datanapps.roomapi.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    val bookTitle: String? = null
    var authorName: String? = null
    val bookImage: String? = null
    val publishedYear: Int = 0
}