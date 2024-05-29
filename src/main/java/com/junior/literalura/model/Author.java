package com.junior.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String name;
    private Double birth;
    private Double death;
    @OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
    }

    public Author(AuthorData d) {
        this.name = d.name();
        this.birth = d.birth_year();
        this.death = d.death_year();
    }

    @Override
    public String toString() {
        return "        AUTOR       " + "\n" +
                "Nombre= " + name + "\n" +
                "Año de nacimiento= " + birth.intValue() + "\n" +
                "Año de fallecimiento= " + death.intValue() + "\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(b -> b.setAuthor(this));
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBirth_year() {
        return birth;
    }

    public void setBirth_year(Double birth_year) {
        this.birth = birth_year;
    }

    public Double getDeath_year() {
        return death;
    }

    public void setDeath_year(Double death_year) {
        this.death = death_year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(Id, author.Id) && Objects.equals(getName(), author.getName()) && Objects.equals(getBirth_year(), author.getBirth_year()) && Objects.equals(getDeath_year(), author.getDeath_year()) && Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, getName(), getBirth_year(), getDeath_year(), books);
    }
}
