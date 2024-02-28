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

            scan.close();

            // QUEMANDO UN USUARIO
            String insert = "INSERT INTO users(id, nombre, role, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setInt(1, 1);
            sqt.setString(2, "proof");
            sqt.setString(3, "admin");
            sqt.setString(4, "pepito@gmail.com");
            sqt.setString(5, "proof");

            sqt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error happened: "+e.getMessage());
        }
    }

    /**
     * verify username and password
     * @return true if user can entry, or false in other case
     */
    public static Boolean userAuth(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String searchUser = "SELECT id FROM users WHERE nombre = ?";
            PreparedStatement st = con.prepareStatement(searchUser);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
