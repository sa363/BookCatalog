package ru.itfb.bookcatalog;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author extends Base {
    @Column(name = "full_name", unique = true)
    private String fullName;

    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "books_id", referencedColumnName = "id"))
    private Set<Book> books = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Author{" +
                "fullName='" + fullName + '\'' +
                ", books=" + books +
                '}';
    }
}