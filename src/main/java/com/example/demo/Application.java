package com.example.demo;

public class Application {
    public static void main(String[] args) {
        System.out.println(new Application().greet());
    }

    public String greet() {
        return "Hello Jenkins!";
    }
}
