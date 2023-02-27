package com.ryanpodell.sbblogrestapi.controller;
import com.ryanpodell.sbblogrestapi.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Ryan",
                "Tompson"
        );
        return student;  //returns this object as a JSON object
    }

    //Want to return list of students as REST API
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "Ryan", "Tommy"));
        students.add(new Student(2, "Dave", "Smith"));
        students.add(new Student(2, "Adam", "Charles"));
        return students;
    }
}
