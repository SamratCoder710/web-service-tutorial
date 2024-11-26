package com.coder.sanam.controller;


import com.coder.sanam.model.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//REST API
@RestController
public class HelloWorldController {

    private final MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
//    @RequestMapping(method = RequestMethod.GET , path = "/hello-world")
    public String sayHello(){
        return "Hello Sanam";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean sayHelloBean(){
        Locale locale = LocaleContextHolder.getLocale();
        String helloWorld = messageSource.getMessage("hello.world.text", null, "Halo World", locale);
        return new HelloWorldBean(helloWorld);
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public String sayHelloToName(@PathVariable String name){
        return "Hello "+ name;
    }
}
