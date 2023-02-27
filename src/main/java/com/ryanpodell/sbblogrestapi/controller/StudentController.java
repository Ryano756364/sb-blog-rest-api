package com.ryanpodell.sbblogrestapi.controller;
import com.ryanpodell.sbblogrestapi.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Ryan",
                "Tompson"
        );
        return student;
    }
}
