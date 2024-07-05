package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends Product{
    private Long isbn;

    public Book(Long isbn) {
        this.isbn = isbn;
    }

    public Book(String name, double price, Long isbn) {
        super(name, price);
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                '}';
    }
}

