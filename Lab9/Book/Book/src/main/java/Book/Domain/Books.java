package Book.Domain;

import java.util.Collection;

public class Books {
    private Collection<Book> books;


    public Books(Collection<Book> books) {
        this.books = books;
    }

    public Collection<Book> getBooks() {
        return books;
    }
}
