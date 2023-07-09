package com.wileymab.bookworm.api.model;

public class Book {
    private Title title;
    private Author author;

    public Book(Title title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
