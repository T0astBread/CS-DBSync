/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.connection.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author m.briedl
 */
public class MsSQLConnector implements Connector
{
    @Override
    public Connection connect(String serverIp, int serverPort, String dbName, String username, String password) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://" + serverIp + ":" + serverPort + ";databaseName=" + dbName, username, password);
    }
}
