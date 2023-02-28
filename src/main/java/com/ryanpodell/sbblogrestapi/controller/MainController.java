package com.ryanpodell.sbblogrestapi.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // contains two annotations @controller and @restcontoller (@restcontroller -> object returned is automatically JSON and passed into HttpResponse object)
public class MainController {
    //All spring mvc classes will remain inside this package

    //HTTP GET Request
    //http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello world";
    }
}
