package com.rhanem.bookseller.repository;

import com.rhanem.bookseller.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
