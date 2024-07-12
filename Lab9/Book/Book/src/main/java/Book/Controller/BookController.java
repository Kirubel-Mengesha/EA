package Book.Controller;


import Book.Domain.Book;
import Book.Domain.Books;
import Book.ExceptionHandling.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private Map<Long, Book> books = new HashMap<Long, Book>();

    public BookController() {
        books.put(1L, new Book(1L,"Ronaldo", "Not Like Us", 29.99));
        books.put(2L, new Book(2L,"Messi", "The Goat", 39.99));
        books.put(3L, new Book(3L,"Rene", "Spring 101", 49.99));
        books.put(4L, new Book(4L,"Neymar", "A day in a life of hospital", 59.99));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        Books allBooks = new Books(books.values());
        return new ResponseEntity<Books>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable Long isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= "
                    + isbn + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam String author) {
        List<Book> foundBooks = books.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
        if (foundBooks.isEmpty()) {
            return new ResponseEntity<>(new CustomErrorType("No books found for author " + author), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Books(foundBooks), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        if (books.containsKey(book.getIsbn())) {
            return new ResponseEntity<>(new CustomErrorType("Book with ISBN " + book.getIsbn() + " already exists"), HttpStatus.CONFLICT);
        }
        books.put(book.getIsbn(), book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable Long isbn, @RequestBody Book book) {
        books.put(isbn, book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable Long isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= " +
                    isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        books.remove(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
