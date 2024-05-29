package com.junior.literalura.repository;

import com.junior.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<List<Author>> findAllByBirthLessThanEqualAndDeathGreaterThanEqual(Double year1, Double year2);
}
