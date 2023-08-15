package com.rhanem.bookseller.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "author", nullable = false, length = 100)
    private String author;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createAt;


}
