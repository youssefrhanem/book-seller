package com.rhanem.bookseller.service;


import com.rhanem.bookseller.model.Book;
import com.rhanem.bookseller.repository.IBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class IBookServiceImpl implements IBookService{


    private IBookRepository bookRepository;


    @Override
    public Book saveBook(Book book) {
        book.setCreateAt(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }


    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
