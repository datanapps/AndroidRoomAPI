package datanapps.roomapi.roomdatabase;

import androidx.room.RoomDatabase;

public abstract class AppDB extends RoomDatabase {
    public abstract DaoBookAccess daoAccess();
}