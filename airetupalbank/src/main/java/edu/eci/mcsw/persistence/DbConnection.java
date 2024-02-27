package edu.eci.mcsw.persistence;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

/**
 * Class to connect with a dataBase
 * 
 */
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/AiretupalDb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String structurePath = "target/classes/database/structure.sql";

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

    /**
     * Method that reads SQL files and execute instructions
     */
    public static void sqlExecutor(){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement st = con.createStatement();
            Scanner scan = new Scanner(new File(structurePath));

            String instruction;
            while (scan.hasNextLine()) {
                instruction = new StringBuilder().append("").append(scan.nextLine()).toString();
                if (!instruction.trim().isEmpty()) {
                    st.executeUpdate(instruction);
                }
            }
        } catch (Exception e) {
            System.out.println("An error happened: "+e.getMessage());
        }
    }
}
