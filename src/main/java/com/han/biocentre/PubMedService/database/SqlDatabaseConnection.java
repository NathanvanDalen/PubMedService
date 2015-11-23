/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nathanvandalen
 */
public class SqlDatabaseConnection {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    private final String url;
    private final String user;
    private final String password;

    public SqlDatabaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection Connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
