/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.connection;

import com.cs.dbsync.connection.connectors.Connector;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author m.briedl
 */
public class DbConnectionEstablisher
{
    private Map<String, Connector> connectors;

    public DbConnectionEstablisher()
    {
        this.connectors = new HashMap<>();
    }
    
    public Connection establishConnection(String dbSystem, String serverIp, int serverPort, String dbName, String username, String password) throws ReflectiveOperationException, SQLException
    {
        return getConnectorToUse(dbSystem).connect(serverIp, serverPort, dbName, username, password);
    }
    
    private Connector getConnectorToUse(String dbSystem) throws ReflectiveOperationException
    {
        return this.connectors.containsKey(dbSystem) ? this.connectors.get(dbSystem) : reflectivelyGetAndCacheConnectorToUse(dbSystem);
    }
    
    private Connector reflectivelyGetAndCacheConnectorToUse(String dbSystem) throws ReflectiveOperationException
    {
        Connector conn = reflectivelyGetConnectorToUse(dbSystem);
        this.connectors.put(dbSystem, conn);
        return conn;
    }
    
    private Connector reflectivelyGetConnectorToUse(String dbSystem) throws ReflectiveOperationException
    {
        Class connectorClass = Class.forName("com.cs.dbsync.connection.connectors." + dbSystem + "Connector");
        return (Connector) connectorClass.newInstance();
    }
}
