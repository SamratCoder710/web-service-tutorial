package com.coder.sanam.controller;


import com.coder.sanam.model.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
//    @RequestMapping(method = RequestMethod.GET , path = "/hello-world")
    public String sayHello(){
        return "Hello Sanam";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean sayHelloBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public String sayHelloToName(@PathVariable String name){
        return "Hello "+ name;
    }
}
