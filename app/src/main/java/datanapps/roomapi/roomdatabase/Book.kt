package datanapps.roomapi.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var bookTitle: String? = null
    var authorName: String? = null
    var publishedYear: Int = 0
}