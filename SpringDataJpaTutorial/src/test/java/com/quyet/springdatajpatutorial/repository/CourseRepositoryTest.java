package com.quyet.springdatajpatutorial.repository;


import com.quyet.springdatajpatutorial.entity.Course;
import com.quyet.springdatajpatutorial.entity.Student;
import com.quyet.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = "+courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("ntb")
                .lastName("b")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }
    @Test
    public void findAllPanigation(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);
        List<Course> courses =courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements= courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

        long totalPages =courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("totalElements = "+totalElements);
        System.out.println("totalPages = "+totalPages);

        System.out.println("course = "+courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0,2, Sort.by("title"));

        Pageable sortByCreditDesc = PageRequest.of(0,2,Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2,Sort.by("title")
                                        .descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("course = "+courses);
    }
    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageTenRecords= PageRequest.of(0,10);

        List<Course> courses =courseRepository.findByTitleContaining("D",firstPageTenRecords).getContent();
        System.out.println("course= "+ courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher =Teacher.builder()
                .firstName("ntc")
                .lastName("c")
                .build();

        Student student = Student.builder()
                .firstName("nva")
                .lastName("a")
                .emailId("nva@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}