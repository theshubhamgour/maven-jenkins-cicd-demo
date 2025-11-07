package com.example.demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTests {
    @Test
    public void testGreeting() {
        Application app = new Application();
        assertEquals("Hello Jenkins!", app.greet());
    }
}
