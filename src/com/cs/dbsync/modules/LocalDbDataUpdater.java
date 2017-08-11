/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class LocalDbDataUpdater extends FileStatementDbDataPusher
{
    public LocalDbDataUpdater() throws IOException
    {
//        super("../queries/LocalDbDataUpdaterQuery.sql");
        super("LocalDbDataUpdaterQuery.sql");
    }
    
    @Override
    public void pushData(ResultSet data, Connection dbConnection) throws Exception
    {
        PreparedStatement statement = null;
        try
        {
            statement = dbConnection.prepareStatement(getStatement());
            statement.setInt(1, data.getInt("ID"));
            incrementAffectedRows(statement.executeUpdate());
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch(Exception e)
            {
                Logger.getLogger(LocalDbDataUpdater.class.getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
}
