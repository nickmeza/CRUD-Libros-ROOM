package com.example.roomappcrud.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomappcrud.entity.Books;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Books... books);

    @Query("SELECT * FROM books")
    List<Books> display();

    @Update
    void update(Books... books);

    @Delete
    void delete(Books ...books);
}
