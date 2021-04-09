package com.example.bookdatabasecollectionrxjava.ui.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.bookdatabasecollectionrxjava.ui.data.dao.BookDao;
import com.example.bookdatabasecollectionrxjava.ui.data.database.Database;
import com.example.bookdatabasecollectionrxjava.ui.data.entities.Author;
import com.example.bookdatabasecollectionrxjava.ui.data.entities.Book;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BookRepository {

    private BookDao mBookDao;

    public BookRepository(Application application){
        Database database = Database.getDatabase(application);
        mBookDao = database.bookDao();
    }

    public Single<List<Book>> getAllBooks() {return mBookDao.getBooks();}

    public Completable populateBooks(){
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();
        Book book5 = new Book();

        book1.setTitle("The Old Man and the Sea");
        book1.setPublishedDate("09/01/1952");
        book1.setAuthor(new Author("Ernest Hemingway", "07/21/1899"));
        book1.setUrl("https://en.wikipedia.org/wiki/The_Old_Man_and_the_Sea");
        bookList.add(book1);

        book2.setTitle("To Kill a Mockingbird");
        book2.setPublishedDate("07/11/1960");
        book2.setAuthor(new Author("Harper Lee", "04/28/1926"));
        book2.setUrl("https://en.wikipedia.org/wiki/To_Kill_a_Mockingbird");
        bookList.add(book2);

        book3.setTitle("The Great Gatsby");
        book3.setPublishedDate("04/10/1925");
        book3.setAuthor(new Author("F. Scott Fitzgerald", "09/24/1896"));
        book3.setUrl("https://en.wikipedia.org/wiki/The_Great_Gatsby");
        bookList.add(book3);

        book4.setTitle("Nineteen Eighty-Four");
        book4.setPublishedDate("06/08/1949");
        book4.setAuthor(new Author("George Orwell", "06/25/1903"));
        book4.setUrl("https://en.wikipedia.org/wiki/Nineteen_Eighty-Four");
        bookList.add(book4);

        book5.setTitle("The Catcher in the Rye");
        book5.setPublishedDate("07/16/1951");
        book5.setAuthor(new Author("J. D. Salinger", "01/01/1919"));
        book5.setUrl("https://en.wikipedia.org/wiki/The_Catcher_in_the_Rye");
        bookList.add(book5);

        return mBookDao.insertBooks(bookList);
    }
}
