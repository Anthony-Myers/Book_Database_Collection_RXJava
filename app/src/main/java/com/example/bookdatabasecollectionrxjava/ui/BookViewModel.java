package com.example.bookdatabasecollectionrxjava.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.bookdatabasecollectionrxjava.ui.data.BookRepository;
import com.example.bookdatabasecollectionrxjava.ui.data.entities.Book;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BookViewModel extends AndroidViewModel {
    private BookRepository mBookRepository;

    public BookViewModel(@NonNull Application application) {
        super(application);
        mBookRepository = new BookRepository(application);
    }

    public Single<List<Book>> getListOfBooks() {
        return mBookRepository.getAllBooks();
    }

    public Completable populateDb() {
        return mBookRepository.populateBooks();
    }
}
