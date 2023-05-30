package com.fincons.itsle.openapi.springbootcrudrestfulwebservicesnojwt.util;

import org.springframework.stereotype.Component;

@Component
public class HandleStrings {

    public String reverse(String input){
        return new StringBuilder(input).reverse().toString();
    }
}
