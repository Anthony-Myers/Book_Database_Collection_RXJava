package com.example.bookdatabasecollectionrxjava.ui.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "books")
public class Book {

    @ColumnInfo
    @PrimaryKey
    @NonNull
    private String title;

    @ColumnInfo
    private String publishedDate;

    @Embedded
    private Author author;

    @ColumnInfo
    private String url;

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getPublishedDate() {return publishedDate;}

    public void setPublishedDate(String publishedDate){this.publishedDate = publishedDate;}

    public Author getAuthor(){return author;}

    public void setAuthor(Author author){this.author = author;}

    public String getUrl(){return url;}

    public void setUrl(String url){this.url = url;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return title.equals(that.title) &&
                publishedDate.equals(that.publishedDate) &&
                author.equals(that.author) &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publishedDate, author, url);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", author=" + author +
                ", url='" + url + '\'' +
                '}';
    }
}
