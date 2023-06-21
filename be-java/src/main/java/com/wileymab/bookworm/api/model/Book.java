package com.wileymab.bookworm.api.model;

public class Book {
    private Title title;
    private Author author;

    public Book() {
    }

    public Book(Title title, Author author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title.getTitle();
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
