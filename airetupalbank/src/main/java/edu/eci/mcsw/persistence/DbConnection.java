package edu.eci.mcsw.persistence;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;


/**
 * Class to connect with a dataBase
 * 
 */
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/AiretupalDb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";


    /**
     * do the connection with a MySql Database
     * @return a Connection with dataBase
     * @throws SQLException if something wrong happens
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
