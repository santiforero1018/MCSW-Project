package edu.eci.mcsw.persistence;

import java.io.File;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;


import edu.eci.mcsw.Model.*;

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
     * Method that verify username and password
     * 
     * @param username the username of the user to verify
     * @param password the password to verify into db
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
     * 
     * @param newUser
     */
    public static void addUser(User newUser) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insert = "INSERT INTO users(nombre, role, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            // sqt.setInt(1, 1);
            sqt.setString(1, newUser.getNombre());
            sqt.setString(2, newUser.getRole());
            sqt.setString(3, newUser.getEmail());
            sqt.setString(4, newUser.getPassword());

            sqt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error ocurred: " + e.getMessage());
        }
    }

    /**
     * method that inserts a bill into db
     * 
     * @param bill the new bill to add into Db
     */
    public static void addBill(Bill bill) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insert = "INSERT INTO bills(reference, price, paid, emisionDate, paidDate, id_cliente) VALUES(?,?,?,?,?,?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setString(1, bill.getReference());
            sqt.setInt(2, bill.getPrice());
            sqt.setString(3, bill.getReference());
            sqt.setString(4, bill.getReference());
            sqt.setString(5, bill.getReference());
            sqt.setString(6, bill.getReference());

            sqt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error ocured: " + e.getMessage());
        }
    }

    /**
     * method to add a service to db
     * 
     * @param service the new service to add into Db
     */
    public static void addService(Service service) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String insert = "INSERT INTO services(nombre, company, consume, ref_bill) VALUES(?,?,?,?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setString(1, service.getNombre());     
            sqt.setString(2, service.getCompany());     
            sqt.setString(3, service.getConsume());     
            sqt.setString(4, service.getBillReference());
            sqt.executeUpdate();     
        } catch (Exception e) {
            System.out.println("An error ocured: " + e.getMessage());
        }
    }

    /**
     * method to add a new account to Db
     * 
     * @param account new account to add to Db
     */
    public static void addAccount(Account account) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String insert = "INSERT INTO accounts(id, accountType, amount, bank, user_id) VALUES(?,?,?,?,?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setString(1, account.getNum());
            sqt.setString(2, account.getType());
            sqt.setInt(3, account.getAmount());
            sqt.setString(4, account.getBank());
            sqt.setInt(5, account.getUser_id());
            sqt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * method that returns users Info
     * 
     * @param name  name of the user to search
     * @param email email of the user to
     * @return an user with general info
     */
    public static User getUser(String name, String email) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String searchUser = "SELECT name, role, email FROM users WHERE name = ? AND email = ?";
            PreparedStatement st = con.prepareStatement(searchUser);
            st.setString(1, name);
            st.setString(2, email);
            ResultSet rs = st.executeQuery();
            return new User(rs.getString("name"), rs.getString("role"), rs.getString("email"));
        } catch (Exception e) {
            System.out.println("can't complete the query: " + e.getMessage());
        }
        return null;
    }

    /**
     * method that get info about the bill
     * 
     * @param reference reference of the bill
     * @return a Bill with general Info
     */
    public static Bill getBill(String reference) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String searchBill = "SELECT reference, price, paid, emisionDate, paidDate, id_cliente FROM bills WHERE reference = ?";
            PreparedStatement st = con.prepareStatement(searchBill);
            st.setString(1, reference);
            ResultSet rs = st.executeQuery();
            Boolean paid = (rs.getString("paid").equals("YES")) ? true : false;
            return new Bill(rs.getString("reference"), rs.getInt("price"), paid, rs.getDate("emisionDate"),
                    rs.getDate("paidDate"), rs.getInt("id_cliente"));
        } catch (Exception e) {
            System.out.println("can't complete the query: " + e.getMessage());
        }
        return null;
    }

    /**
     * method that return Service info
     * 
     * @param id
     * @return a service with general info
     */
    public static Service getService(int id) {

        return null;
    }

    /**
     * Method that return an account if account id and username match
     * 
     * @param id       account id to search
     * @param username name of the user to match with account Id
     * @return
     */
    public static Optional<?> geAccount(String id, String username) {
        return null;
    }

}
