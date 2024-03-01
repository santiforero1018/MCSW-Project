package edu.eci.mcsw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import edu.eci.mcsw.persistence.DbConnection;
import edu.eci.mcsw.server.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        try {
            WebServer.startServer();
            Connection con = DbConnection.getConnection();
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Couldn't start the server: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Couldn Connect to Database: " + e.getMessage());
        }
    }
}
