package com.example.sparkapp;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class for Spark Web Application
 */
public class App {
    
    private static final Gson gson = new Gson();
    private static final long startTime = System.currentTimeMillis();
    
    public static void main(String[] args) {
        // Set the port (default 4567)
        port(getPort());
        
        // Configure CORS for all routes
        enableCORS("*", "*", "*");
        
        // Define routes
        setupRoutes();
        
        System.out.println("Server started on port " + getPort());
        System.out.println("Visit http://localhost:" + getPort() + " in your browser");
    }
    
    /**
     * Setup all application routes
     */
    private static void setupRoutes() {
        // Root endpoint - HTML response
        get("/", (req, res) -> {
            res.type("text/html");
            return "<html><body>" +
                   "<h1>Welcome to Spark Web Application!</h1>" +
                   "<p>A simple Java web application using the Spark framework.</p>" +
                   "<h2>Available Endpoints:</h2>" +
                   "<ul>" +
                   "<li><a href='/'>/ - This page</a></li>" +
                   "<li><a href='/hello'>GET /hello - Say hello</a></li>" +
                   "<li><a href='/hello/World'>GET /hello/:name - Personalized greeting</a></li>" +
                   "<li><a href='/api/info'>GET /api/info - API information (JSON)</a></li>" +
                   "<li><a href='/api/status'>GET /api/status - Server status (JSON)</a></li>" +
                   "</ul>" +
                   "</body></html>";
        });
        
        // Simple hello endpoint
        get("/hello", (req, res) -> "Hello from Spark!");
        
        // Hello with name parameter
        get("/hello/:name", (req, res) -> {
            String name = req.params(":name");
            return "Hello, " + name + "!";
        });
        
        // API info endpoint - returns JSON
        get("/api/info", (req, res) -> {
            res.type("application/json");
            Map<String, Object> info = new HashMap<>();
            info.put("application", "Spark Web Application");
            info.put("version", "1.0.0");
            info.put("framework", "Spark Java");
            info.put("description", "A simple Java web application");
            return gson.toJson(info);
        });
        
        // API status endpoint - returns JSON
        get("/api/status", (req, res) -> {
            res.type("application/json");
            Map<String, Object> status = new HashMap<>();
            status.put("status", "running");
            status.put("timestamp", System.currentTimeMillis());
            status.put("uptime", getUptime());
            return gson.toJson(status);
        });
        
        // Handle 404 - Not Found
        notFound((req, res) -> {
            res.type("application/json");
            Map<String, String> error = new HashMap<>();
            error.put("error", "Not Found");
            error.put("message", "The requested resource was not found");
            error.put("path", req.pathInfo());
            return gson.toJson(error);
        });
        
        // Handle 500 - Internal Server Error
        internalServerError((req, res) -> {
            res.type("application/json");
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal Server Error");
            error.put("message", "An unexpected error occurred");
            return gson.toJson(error);
        });
    }
    
    /**
     * Enable CORS for all routes
     */
    private static void enableCORS(final String origin, final String methods, final String headers) {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
        });
    }
    
    /**
     * Get the port from environment variable or use default
     */
    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 4567; // Default Spark port
    }
    
    /**
     * Get application uptime in seconds
     */
    private static long getUptime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
