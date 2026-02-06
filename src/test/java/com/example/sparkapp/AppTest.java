package com.example.sparkapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Spark Web Application
 */
public class AppTest {
    
    @Test
    public void testAppInitialization() {
        // Basic test to ensure the App class can be instantiated
        assertNotNull(new App());
    }
    
    @Test
    public void testEnvironmentPortSetting() {
        // Test that port can be configured via environment
        // This is a simple test to verify the test infrastructure works
        String port = System.getenv("PORT");
        if (port != null) {
            assertTrue(Integer.parseInt(port) > 0);
        }
    }
}
