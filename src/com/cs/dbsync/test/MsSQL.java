/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.test;

import com.cs.dbsync.utils.FileUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class MsSQL
{
    public static void main(String[] args) throws IOException
    {
        Connection dbConnection = null;
        Properties props = new Properties();
        props.put("usingPassword", "NO");
        props.put("user", "sa");
        props.put("password", "sapwd");
        Statement statement = null;
        ResultSet results = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dbConnection = DriverManager.getConnection("jdbc:sqlserver://SR038:1433;databaseName=Homepage", props);
            
            statement = dbConnection.createStatement();
            statement.execute(FileUtils.readWholeFile(MsSQL.class.getResourceAsStream("../queries/LocalDbDataFetcherQuery.sql")));
            
            results = statement.getResultSet();
            while(results.next())
            {
                String v = results.getString("VNR");
                if(v == null) continue;
                System.out.println(v);
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(MsSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                dbConnection.close();
            }
            catch (Exception ex)
            {
                Logger.getLogger(MsSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                statement.close();
            }
            catch (Exception ex)
            {
                Logger.getLogger(MsSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                results.close();
            }
            catch (Exception ex)
            {
                Logger.getLogger(MsSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
