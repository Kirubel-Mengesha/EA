package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		StopWatch sw = new StopWatch();
		sw.start();
		computeSave10000();
		sw.stop();

		System.out.println("---------saving compute------------- ");
//		System.out.println(sw.prettyPrint());
		System.out.println("save in second "+sw.getTotalTimeSeconds());

		sw.start();
		computeRetrieve10000();
		sw.stop();

		System.out.println("---------retrieve compute------------- ");
		System.out.println("retrieve in second "+sw.getTotalTimeSeconds());

	}

	private void computeRetrieve10000() {
		personRepository.findAll();
	}

	public void computeSave10000(){
		for (int i = 1; i<= 10000; i++ ){
			Person person = new Person(i,"First "+i,"Last "+i);
			for (int j = 1; j <=10 ; j++) {
				Pet pt = new Pet("p"+j,i);
				person.addPet(pt);
			}
			personRepository.save(person);
		}
	}
}
