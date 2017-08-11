/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.connection.connectors;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author m.briedl
 */
public interface Connector
{
    Connection connect(String serverIp, int serverPort, String dbName, String username, String password) throws ClassNotFoundException, SQLException;
}
