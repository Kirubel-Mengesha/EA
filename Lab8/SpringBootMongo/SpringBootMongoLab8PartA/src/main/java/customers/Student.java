package customers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document

public class Student {
    @Id
    private long studentNumber;
    private String name;
    private String phone;

    private Address address;

    private List<Grade> gradeList = new ArrayList<>();

    public Student(long studentNumber, String name, String phone, Address address) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Student() {
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public void addGrade(Grade g){
        gradeList.add(g);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
