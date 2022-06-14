package ru.itfb.bookcatalog;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
@EqualsAndHashCode(exclude = "authors")
public class Book extends Base {
    @Column(name = "name")
    private String name;

    @Column(name = "review")
    private String review;

    @Column(name = "style")
    private String style;

    @ManyToMany
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

}