package edu.eci.mcsw.persistence;

import java.sql.*;

/**
 * Class to connect with a dataBase
 * 
 */
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/AiretupalDb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    /**
     * Method that creates a users table into database
     */
    public static void createUsersTable() {
        String createdUT = "CREATE TABLE users(id INT PRIMARY KEY, nombre VARCHAR(50), role VARCHAR(50), password VARCHAR(50))";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("conexion realizada");
            Statement st = con.createStatement();
            st.execute(createdUT);
            System.out.println("Tabla creada");

        } catch (SQLException e) {
            System.out.println("Problems with connection: " + e.getMessage());
        }
    }

}
