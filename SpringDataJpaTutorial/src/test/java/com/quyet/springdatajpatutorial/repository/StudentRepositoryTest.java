package com.quyet.springdatajpatutorial.repository;

import com.quyet.springdatajpatutorial.entity.Guardian;
import com.quyet.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("nhq@gmail.com")
                .firstName("nhq")
                .lastName("quyet")
                //.guardianName("nhd")
                //.guardianEmail("nhd@gmail.com")
                //.guardianMobile("11111111")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("ntb@gmail.com")
                .name("ntb")
                .mobile("999999")
                .build();

        Student student = Student.builder()
                .firstName("nht")
                .emailId("nht@gamil.com")
                .lastName("thang")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("StudentList= "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students= studentRepository.findByFirstName("nht");
        System.out.println("student= "+students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students= studentRepository.findByFirstNameContaining("t");
        System.out.println("student= "+students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =studentRepository.findByGuardianName("nhd");
        System.out.println("student= "+students);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("nhq@gmail.com");
        System.out.println("student= "+student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String student = studentRepository.getStudentFirstNameByEmailAddress("nhq@gmail.com");
        System.out.println("student= "+student);
    }
}