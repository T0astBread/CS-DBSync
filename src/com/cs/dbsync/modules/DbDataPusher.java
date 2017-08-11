/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author m.briedl
 */
public abstract class DbDataPusher implements DataHandler
{
    private Connection dbConnection;
    private int affectedRows;

    public Connection getDbConnection()
    {
        return dbConnection;
    }

    public DbDataPusher setDbConnection(Connection dbConnection)
    {
        this.dbConnection = dbConnection;
        return this;
    }
    
    @Override
    public void handleData(ResultSet data) throws Exception
    {
        pushData(data, this.dbConnection);
    }
    
    public abstract void pushData(ResultSet data, Connection dbConnection) throws Exception;

    public int getAffectedRows()
    {
        return affectedRows;
    }
    
    public void incrementAffectedRows(int amount)
    {
        this.affectedRows += amount;
    }
    
    public void resetAffectedRows()
    {
        this.affectedRows = 0;
    }
}
