package com.example.bookdatabasecollectionrxjava.ui.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bookdatabasecollectionrxjava.ui.data.entities.Book;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class BookDao {

    @Query("SELECT * FROM books")
    public abstract Single<List<Book>> getBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Completable insertBooks(List<Book> book);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Completable insertBook(Book book);

    @Query("DELETE FROM books")
    public abstract Completable deleteBooks();
}
