package com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.controller;


import com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.entity.User;
import com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.util.HandleStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("greetings")
public class GreetingsController {

    @Autowired
    private HandleStrings handleStrings;

    @GetMapping
    public String greetings(){
        return "Greetings";
    }

    @PostMapping
    public String greetingsFromName(@RequestBody User user){
        return "Greetings from " + user.getFirstName() + " " + user.getLastName();
    }

    @PostMapping("reversed")
    public User greetingsFromReversedUser(@RequestBody User user){
        var newUser = new User();
        newUser.setFirstName(reverse(user.getFirstName()));
        newUser.setLastName(reverse(user.getLastName()));
        return newUser;
    }

    private String reverse(String input){
        return new StringBuilder(input).reverse().toString();
    }
}
