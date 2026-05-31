package com.example.ecommerceproject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //tells the compilor that the below class contains API
public class FirstAPI {
    @RequestMapping("/hello") // tells the compilor that this is the endpoint to call sayhello
    public String sayHello(){
        return "Hello user";
    }

    @RequestMapping("/hello/{name}") // tells the compilor that this is the endpoint to call sayhello
    public String sayHelloToAPerson(@PathVariable("name") String name){
        return "Hello " + name;
    }

}
