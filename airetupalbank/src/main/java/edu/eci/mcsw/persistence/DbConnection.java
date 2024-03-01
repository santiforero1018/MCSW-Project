package edu.eci.mcsw.persistence;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

import edu.eci.mcsw.Model.Bill;
import edu.eci.mcsw.Model.Service;
import edu.eci.mcsw.Model.User;

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
    public static void sqlExecutor() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
            String insert = "INSERT INTO users(nombre, role, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            // sqt.setInt(1, 1);
            sqt.setString(1, "proof");
            sqt.setString(2, "admin");
            sqt.setString(3, "pepito@gmail.com");
            sqt.setString(4, "proof");

            sqt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error happened: " + e.getMessage());
        }
    }

    /**
     * verify username and password
     * 
     * @return true if user can entry, or false in other case
     */
    public static Boolean userAuth(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String searchUser = "SELECT id FROM users WHERE nombre = ? AND password = ?";
            PreparedStatement st = con.prepareStatement(searchUser);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method to add a new user into db 
     * @param newUser
     */
    public static void addUser(User newUser) {
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String insert = "INSERT INTO users(nombre, role, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            // sqt.setInt(1, 1);
            sqt.setString(1, newUser.getNombre());
            sqt.setString(2, newUser.getRole());
            sqt.setString(3, newUser.getEmail());
            sqt.setString(4, newUser.getPassword());

            sqt.executeUpdate();
        } catch(Exception e){
            System.out.println("An erros ocurred: "+ e.getMessage());
        }
    }

    /**
     * 
     */
    public static void addBill(Bill bill){
        
    }

    /**
     * method to add a service to db
     * @param service 
     */
    public static void addService(Service service){
        
    }

    public static User getUser(String name, String email){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String searchUser = "SELECT name, role, email FROM users WHERE name = ? AND email = ?";
            PreparedStatement st = con.prepareStatement(searchUser);
            st.setString(1, name);
            st.setString(2, email);
            ResultSet rs = st.executeQuery();
            return new User(rs.getString("name"), rs.getString("role"), rs.getString("email"));
        } catch (Exception e) {
            System.out.println("An erros ocurred: "+ e.getMessage());
        }
        return null;
    }   

    public static Bill getBill(String reference){

        return null;
    }

    public static Service getService(int id){
        return null;
    }


}
