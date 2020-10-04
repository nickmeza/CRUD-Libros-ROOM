package com.example.roomappcrud.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Books {

    @PrimaryKey
    @ColumnInfo(name = "book_id")
    private Integer bookId;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "author_name")
    private String authorName;
    @ColumnInfo(name = "book_price")
    private Double bookPrice;
    @ColumnInfo(name = "book_description")
    private String bookDescription;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
