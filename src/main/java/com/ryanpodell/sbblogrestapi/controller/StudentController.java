package com.ryanpodell.sbblogrestapi.controller;
import com.ryanpodell.sbblogrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    //Want to set up path variable with method
    //{id} - URI template variable
    //http://localhost:8080/students/1
    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable("id") int StudentId){
        return new Student(StudentId, "Ryan", "LastName");
    }

    //http://localhost:8080/students/1/ryan/odell
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentFullPathVariable(
            @PathVariable("id") int StudentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName){
        return new Student(StudentId, firstName, lastName);
    }

    //Spring boot REST API with Request Param
    //http://localhost:8080/students?id=1
    @GetMapping("students/query?id=1")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id, "StudentFirstName", "StudentLastName");
    }

    //Spring boot REST API with multiple Request Param
    //http://localhost:8080/students?id=1&firstname=Ryan&lastName=Thomas
    //User sends in ID, first name, and last name, then student object is returned
    @GetMapping("students/query")
    public Student studentRequestMultipleVariable(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastname){
        return new Student(id, firstName, lastname);
    }

    //PathVariable versus RequestParam
    //PathVariable is used to bind URI data to method
    //RequestParam extracts parameters from URI

    //Spring boot REST API that handles HTTP POST Request
    //Need @PostMapping and @RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Put request to updating existing data
    @PutMapping("students/{id}/update")
    public Student updateStudent(
            @RequestBody Student student,  //remember that this grabs the JSON body and converts to Java object
            @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Delete data
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully!";
    }
}
