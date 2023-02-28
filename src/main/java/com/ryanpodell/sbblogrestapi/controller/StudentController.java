package com.ryanpodell.sbblogrestapi.controller;
import com.ryanpodell.sbblogrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Ryan",
                "Tompson"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);  //returns this object as a JSON object
        return ResponseEntity.ok().header(
                "custom-header",
                "ryanHeaderValue")
                .body(student); //same as line above
    }

    //Want to return list of students as REST API
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "Ryan", "Tommy"));
        students.add(new Student(2, "Dave", "Smith"));
        students.add(new Student(2, "Adam", "Charles"));
        return ResponseEntity.ok(students);
    }

    //Want to set up path variable with method
    //{id} - URI template variable
    //http://localhost:8080/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int StudentId){
        Student student = new Student(StudentId, "Ryan", "LastName");
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students/1/ryan/odell
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentFullPathVariable(
            @PathVariable("id") int StudentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName){
        Student student = new Student(StudentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot REST API with Request Param
    //http://localhost:8080/students?id=1
    @GetMapping("query?id=1")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id){
        Student student = new Student(id, "StudentFirstName", "StudentLastName");
        return ResponseEntity.ok(student);
    }

    //Spring boot REST API with multiple Request Param
    //http://localhost:8080/students?id=1&firstname=Ryan&lastName=Thomas
    //User sends in ID, first name, and last name, then student object is returned
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestMultipleVariable(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastname){
        Student student = new Student(id, firstName, lastname);
        return ResponseEntity.ok(student);
    }

    //PathVariable versus RequestParam
    //PathVariable is used to bind URI data to method
    //RequestParam extracts parameters from URI

    //Spring boot REST API that handles HTTP POST Request
    //Need @PostMapping and @RequestBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);  //not using 'ok' because 'ok' returns 200, we need 201 here
    }

    //Put request to updating existing data
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(
            @RequestBody Student student,  //remember that this grabs the JSON body and converts to Java object
            @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    //Delete data
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
