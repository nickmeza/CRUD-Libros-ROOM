package com.example.roomappcrud.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomappcrud.entity.Books;

@Database(entities = {Books.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
