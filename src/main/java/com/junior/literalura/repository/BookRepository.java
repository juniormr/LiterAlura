package com.junior.literalura.repository;

import com.junior.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByLanguageIs(String language);
}
