package com.example.demo.exceptions;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message){
        super(message);
    }
}
