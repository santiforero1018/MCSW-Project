package edu.eci.mcsw;

import java.io.IOException;

import edu.eci.mcsw.server.*;

/**
 * Main class
 *
 */
public class App {
    public static void main(String[] args) {

        try {
        WebServer.startServer();
        } catch (IOException e) {
            System.out.println("Couldn't start the server: " + e.getMessage());
        } 
    }
}
