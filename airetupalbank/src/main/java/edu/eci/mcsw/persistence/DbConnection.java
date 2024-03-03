package edu.eci.mcsw.persistence;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    private static final String deletePath = "target/classes/database/xData.sql";

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
     * Method that execute delete instructions to delete Data
     */
    public static void sqlDeleteData() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement st = con.createStatement();
            Scanner scan = new Scanner(new File(deletePath));

            String instruction;
            while (scan.hasNextLine()) {
                instruction = new StringBuilder().append("").append(scan.nextLine()).toString();
                if (!instruction.trim().isEmpty()) {
                    st.executeUpdate(instruction);
                }
            }

            scan.close();

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
            String insert = "INSERT INTO bills(price, paid, emisionDate, paidDate, id_cliente) VALUES(?,?,?,?,?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setInt(1, bill.getPrice());
            String paidDat = bill.getPaid().toString();
            sqt.setString(2, paidDat);
            sqt.setDate(3, bill.getEmisionDate());
            sqt.setDate(4, bill.getPaidDate());
            sqt.setInt(5, bill.getUser_id());

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
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insert = "INSERT INTO services(nombre, company, consume, ref_bill) VALUES(?,?,?,?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setString(1, service.getNombre());
            sqt.setString(2, service.getCompany());
            sqt.setInt(3, service.getConsume());
            sqt.setString(4, service.getBillReference());
            sqt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error happenned: " + e.getMessage());
        }
    }

    /**
     * method to add a new account to Db
     * 
     * @param account new account to add to Db
     */
    public static void addAccount(Account account) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insert = "INSERT INTO accounts(id, accountType, amount, bank, user_id) VALUES(?,?,?,?,?)";
            PreparedStatement sqt = con.prepareStatement(insert);
            sqt.setString(1, account.getNum());
            sqt.setString(2, account.getType());
            sqt.setInt(3, account.getAmount());
            sqt.setString(4, account.getBank());
            sqt.setInt(5, account.getUser_id());
            sqt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error happened trying to add Accounts: " + e.getMessage());
        }
    }

    /**
     * method that returns users Info
     * 
     * @param name  name of the user to search
     * @param email email of the user to
     * @return an user with general info or return empty
     */
    public static Optional<User> getUser(String name, String email) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String searchUser = "SELECT name, role, email FROM users WHERE name = ? AND email = ?";
            PreparedStatement st = con.prepareStatement(searchUser);
            st.setString(1, name);
            st.setString(2, email);
            ResultSet rs = st.executeQuery();
            return (!rs.next()) ? Optional.empty()
                    : Optional.ofNullable(new User(rs.getString("name"), rs.getString("role"), rs.getString("email")));
        } catch (Exception e) {
            System.out.println("can't complete the query: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * method that get info about the bill
     * 
     * @param reference reference of the bill
     * @return a Bill with general Info or empty
     */
    public static Optional<Bill> getBill(String reference) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String searchBill = "SELECT reference, price, paid, emisionDate, paidDate, id_cliente FROM bills WHERE reference = ?";
            PreparedStatement st = con.prepareStatement(searchBill);
            st.setString(1, reference);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Boolean paid = rs.getString("paid").equals("true");
                return Optional.ofNullable(
                        new Bill(rs.getString("reference"), rs.getInt("price"), paid, rs.getDate("emisionDate"),
                                rs.getDate("paidDate"), rs.getInt("id_cliente")));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("Can't complete the query: " + e.getMessage());
            return Optional.empty();
        }

    }

    /**
     * method that return Service info
     * 
     * @param id
     * @return a service with general info or empty
     */
    public static Optional<Service> getService(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT id, nombre, company, consume, ref_bill FROM services WHERE id = ?";
            PreparedStatement sqt = con.prepareStatement(query);
            sqt.setInt(1, id);
            ResultSet rs = sqt.executeQuery();

            return (rs.next()) ? Optional.ofNullable(new Service(rs.getString("nombre"), rs.getString("company"),
                    rs.getInt("consume"), rs.getString("ref_bill"))) : Optional.empty();

        } catch (Exception e) {
            System.out.println("Couldn't exec the query of service: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Method that return an account if account id and username match
     * 
     * @param id       account id to search
     * @param username name of the user to match with account Id
     * @return general info about an account or empty
     */
    public static Optional<Account> getAccount(String id, String username) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM accounts WHERE id = ? AND user_id = (SELECT id FROM users WHERE nombre = ?)";
            PreparedStatement sqt = con.prepareStatement(query);
            sqt.setString(1, id);
            sqt.setString(2, username);
            ResultSet rs = sqt.executeQuery();
            return (rs.next())
                    ? Optional.ofNullable(new Account(rs.getString("id"), rs.getString("accountType"),
                            rs.getInt("amount"), rs.getString("bank"), rs.getInt("user_id")))
                    : Optional.empty();
        } catch (Exception e) {
            System.out.println("An error happened trying to get account: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Method to do a transaction to pay a bill
     * 
     * @param accountOrigin account of the user to pay the bill
     * @param billRef       bill reference to pay
     * @param amount        amount to pay the bill
     * @return a boolean, true if transaction was succesfuly or false if doesn't it.
     */
    public static Boolean transaction(String accountOrigin, String billRef) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String queryAccount = "SELECT bills.reference, accounts.id, bills.price, accounts.amount FROM bills JOIN users ON(bills.id_cliente = users.id) JOIN accounts ON(users.id = accounts.user_id) WHERE accounts.id = ? AND bills.reference = ?";
            PreparedStatement query = con.prepareStatement(queryAccount);
            query.setString(1, accountOrigin);
            query.setString(2, billRef);
            ResultSet st = query.executeQuery();
            if(st.next()){
                int billPrice = st.getInt("price"), amount = st.getInt("amount");
                if(amount < billPrice){
                    System.out.println("An error insite the operation, please verify info ");
                    return false;
                } else{

                    // Calculo de la fecha de pago
                    LocalDate fechaActual = LocalDate.now();
                    ZonedDateTime fechaHoraInicio = fechaActual.atStartOfDay(ZoneId.systemDefault());
                    Date paiDate = new Date(fechaHoraInicio.toInstant().toEpochMilli());

                    // calculo del monto final de la cuenta del usuario
                    int finalAmount = amount - billPrice;

                    //Preparando sentencias para actualizar 
                    String trans = "UPDATE bills SET paid = ?, paidDate = ? WHERE reference = ?";
                    String pay = "UPDATE accounts SET amount = ? WHERE id = ?";
                    PreparedStatement updateBill = con.prepareStatement(trans);
                    PreparedStatement updateAccount = con.prepareStatement(pay);

                    // pasando parametros de actualizacion de la factura
                    updateBill.setString(1, "true");
                    updateBill.setDate(2, paiDate);
                    updateBill.setString(3, billRef);

                    // pasando parametros de actualizacion de la cuenta 
                    updateAccount.setInt(1, finalAmount);
                    updateAccount.setString(2, accountOrigin);

                    //ejecutar sentencias de actualización
                    updateBill.executeUpdate();
                    updateAccount.executeUpdate();

                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("An error happened trying to transfer: " + e.getMessage());
            return false;
        }
    }

}