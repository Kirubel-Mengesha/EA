package Book;

import Book.Domain.Book;
import io.micrometer.observation.annotation.Observed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		RestClient restClient = RestClient.builder()
//				.baseUrl("http://localhost:8080")
//				.build();

		//add book
//		Book ea = restClient.post()
//				.uri("/books")
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(new Book(5, "Erick", "We don't care", 129.99))
//				.retrieve()
//				.body(Book.class);
		//get Book the goat
//		Book theGoat = restClient.get()
//				.uri("/books/{isbn}", 4)
//				.retrieve()
//				.body(Book.class);
//		System.out.println(theGoat);

		//delete book
//		 restClient.delete()
//				.uri("/books/{isbn}", 2)
//				.retrieve()
//				.toBodilessEntity();

		// update the goat
//		theGoat.setAuthor("Zidane");
//		ea = restClient.post()
//				.uri("/books")
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(ea)
//				.retrieve()
//				.body(Book.class);
	}
}


