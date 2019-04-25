package datanapps.roomapi.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun daoBookAccess(): DaoBookAccess

}