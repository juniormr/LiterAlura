package com.junior.literalura.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String title;
    private String language;
    private double download_count;

    @ManyToOne()
    private Author author;

    public Book() {
    }

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.language = bookData.languages().get(0);
        this.download_count = bookData.download_count();
    }

    @Override
    public String toString() {
        return "        LIBRO       " + "\n" +
                "Titulo= " + title + "\n" +
                "Autor= " + author.getName() + "\n" +
                "Lenguaje= " + language + "\n" +
                "Número de descargas= " + download_count + "\n";
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {

        this.author = author;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getDownload_count() {
        return download_count;
    }

    public void setDownload_count(double download_count) {
        this.download_count = download_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Double.compare(getDownload_count(), book.getDownload_count()) == 0 && Objects.equals(getId(), book.getId()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getLanguage(), book.getLanguage()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getLanguage(), getDownload_count(), getAuthor());
    }
}
