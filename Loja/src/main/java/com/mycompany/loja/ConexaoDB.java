package com.mycompany.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/loja";
    private static final String USER = "root"; // Altere conforme suas credenciais
    private static final String PASSWORD = "1234"; // Altere conforme sua senha

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

