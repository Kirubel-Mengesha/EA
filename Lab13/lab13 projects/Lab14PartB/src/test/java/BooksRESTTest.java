import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878", "Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn", equalTo("878"))
                .body("title", equalTo("Book 123"))
                .body("price", equalTo(18.95f))
                .body("author", equalTo("Joe Smith"));
        // cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testAddBook() {
        Book book = new Book("123", "New Book", 25.99, "John Doe");

        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200)
                .and()
                .body("isbn", equalTo("123"))
                .body("title", equalTo("New Book"))
                .body("price", equalTo(25.99f))
                .body("author", equalTo("John Doe"));
        // cleanup
        given()
                .when()
                .delete("books/123");
    }

    @Test
    public void testUpdateBook() {
        // add the book to be updated
        Book book = new Book("456", "Update Book", 19.99, "Jane Doe");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        // update the book
        Book updatedBook = new Book("456", "Updated Book Title", 29.99, "Jane Doe");
        given()
                .contentType("application/json")
                .body(updatedBook)
                .when().put("/books/456").then()
                .statusCode(200)
                .and()
                .body("isbn", equalTo("456"))
                .body("title", equalTo("Updated Book Title"))
                .body("price", equalTo(29.99f))
                .body("author", equalTo("Jane Doe"));
        // cleanup
        given()
                .when()
                .delete("books/456");
    }

    @Test
    public void testDeleteBook() {
        // add the book to be deleted
        Book book = new Book("789", "Delete Book", 9.99, "Mark Twain");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        // delete the book
        given()
                .when()
                .delete("books/789").then()
                .statusCode(204);

        // verify deletion
        given()
                .when()
                .get("books/789").then()
                .statusCode(404);
    }

    @Test
    public void testGetAllBooks() {
        // add books to be fetched
        Book book1 = new Book("101", "Book One", 15.99, "Author A");
        Book book2 = new Book("102", "Book Two", 17.99, "Author B");

        given()
                .contentType("application/json")
                .body(book1)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);

        // test getting all books
        given()
                .when()
                .get("books")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("books.size()", is(2))
                .body("books.isbn", hasItems("101", "102"))
                .body("books.title", hasItems("Book One", "Book Two"))
                .body("books.price", hasItems(15.99f, 17.99f))
                .body("books.author", hasItems("Author A", "Author B"));

        // cleanup
        given()
                .when()
                .delete("books/101");

        given()
                .when()
                .delete("books/102");
    }

    @Test
    public void testGetBooksByAuthor() {
        // add books to be fetched by author
        Book book1 = new Book("201", "Author Book One", 12.99, "Author X");
        Book book2 = new Book("202", "Author Book Two", 22.99, "Author X");
        Book book3 = new Book("203", "Different Author Book", 32.99, "Author Y");

        given()
                .contentType("application/json")
                .body(book1)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .contentType("application/json")
                .body(book3)
                .when().post("/books").then()
                .statusCode(200);

        // test getting books by author
        given()
                .when()
                .get("books?author=\"Author X\"")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("books.size()", is(2))
                .body("books.isbn", hasItems("201", "202"))
                .body("books.title", hasItems("Author Book One", "Author Book Two"))
                .body("books.price", hasItems(12.99f, 22.99f))
                .body("books.author", hasItems("Author X"));

        // cleanup
        given()
                .when()
                .delete("books/201");

        given()
                .when()
                .delete("books/202");

        given()
                .when()
                .delete("books/203");
    }
}
