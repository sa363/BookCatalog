package ru.itfb.bookcatalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book extends Base {
    @Column(name = "name")
    private String name;

    @Column(name = "review")
    private String review;

    @Column(name = "style")
    private String style;

    @ManyToMany
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("books")
    private Set<Author> authors = new LinkedHashSet<>();

    public boolean addAuthor(Author author) {
       return this.getAuthors().add(author);
    }

    public boolean removeAuthor(Author author) {
       return this.getAuthors().remove(author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}