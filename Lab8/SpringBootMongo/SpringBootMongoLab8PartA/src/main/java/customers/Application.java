package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // create Address
		Address address1 = new Address("1000 N 4th St", "Fairfield", "52557");
		Address address2 = new Address("1000 N 6th St", "Easton", "52511");
		Address address3 = new Address("1000 N 7th St", "Ottawa", "52990");
		// create students

		Student student1 = new Student(101,"John doe", "123-456-789", address1);
		Student student2 = new Student(102,"Bob marley", "434-567-987", address2);
		Student student3 = new Student(103,"Eric Ten hag", "333-444-666", address3);

		student1.addGrade(new Grade("EA","A-"));
		student2.addGrade(new Grade("SA","A"));
		student3.addGrade(new Grade("ASD","A+"));

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);


		//get students
		System.out.println("----------findByName-------");
		System.out.println(studentRepository.findByName("John doe"));

		System.out.println("-----------findByPhone ----------------");
		System.out.println(studentRepository.findByPhone("123-456-789"));

		System.out.println("-----------findByCity ----------------");
		System.out.println(studentRepository.findByAddressCity("Fairfield"));

		System.out.println("-----------findByCourseName ----------------");
		System.out.println(studentRepository.findByGradeListCourseName("EA"));

		System.out.println("-----------findByGrade ----------------");
		System.out.println(studentRepository.findByGradeListCourseNameAndGradeListGrade("SA","A"));


	}

}
