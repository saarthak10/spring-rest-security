package com.luv2code.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// creating a demo controller to see thymeleaf demo for detailed implementation it will be found in thymeleaf crud demo repo
@Controller
public class DemoController {
  //create mapping for /hello
    @GetMapping("/hello")
    public String sayHello(Model theModel){
        theModel.addAttribute("theData",new java.util.Date());
        return "helloWorld";
    }

}
