package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws IOException {
        final int port = 8080;
        HttpServer server = HttpServer.create( new InetSocketAddress("0.0.0.0", port), 10);
        server.createContext("/build", new MyHandler());

        System.out.println("Server starting on port: " + port);
        server.start();
        System.out.println("Contexts created: /build");
    }

}

class MyHandler implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {
        String requestMethod = t.getRequestMethod();
        URI requestUri = t.getRequestURI();
        InputStream in = t.getRequestBody();
        String text = IOUtils.toString(in, StandardCharsets.UTF_8.name());
        ObjectMapper mapper = new ObjectMapper();
        Bindings bindings = mapper.readValue(text, Bindings.class);
        String str = requestMethod + " " + requestUri.getRawQuery() + " " + bindings.toString();
        String json = mapper.writeValueAsString(str);
        t.getResponseHeaders().add("Content-Type", "application/json");
        t.sendResponseHeaders(200, json.length());
        t.getResponseBody().write(json.getBytes());
        t.getResponseBody().close();
        t.close();
    }

}