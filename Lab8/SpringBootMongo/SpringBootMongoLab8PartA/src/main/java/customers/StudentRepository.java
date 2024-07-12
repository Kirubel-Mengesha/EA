package customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {

    List<Student>findByName(String name);
    List<Student> findByPhone(String phone);

    List<Student> findByAddressCity(String city);

    //Find the Students that took a certain course with a given name
    List<Student> findByGradeListCourseName(String courseName);

    //Find the Students with an A+ for a certain course name
    List<Student> findByGradeListCourseNameAndGradeListGrade(String courseName, String grade);




}

