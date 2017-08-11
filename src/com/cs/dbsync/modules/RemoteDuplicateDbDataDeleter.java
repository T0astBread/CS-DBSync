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
public class RemoteDuplicateDbDataDeleter extends FileStatementDbDataPusher
{
    public RemoteDuplicateDbDataDeleter() throws IOException
    {
//        super("../queries/RemoteDuplicateDbDataDeleterQuery.sql");
        super("RemoteDuplicateDbDataDeleterQuery.sql");
    }
    
    @Override
    public void pushData(ResultSet data, Connection dbConnection) throws Exception
    {
        PreparedStatement statement = null;
        try
        {
            statement = dbConnection.prepareStatement(getStatement());
            statement.setInt(1, data.getInt("ID"));
//            System.out.println("Deleted " + statement.executeUpdate() + " duplicate rows...");
            incrementAffectedRows(statement.executeUpdate());
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch (Exception e)
            {
                Logger.getLogger(RemoteDuplicateDbDataDeleter.class.getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
}
