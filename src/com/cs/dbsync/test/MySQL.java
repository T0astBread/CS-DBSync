/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class MySQL
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://mysqlsvr39.world4you.com:3306/2654128db1", "sql3684033", "s*tcyqm");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs-website", "root", "");

            statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO news_categories (name, cs_match) VALUES('abc', 'a'); " + 
                            "INSERT INTO news_categories (name, cs_match) VALUES('cbc', 'c')");

//            results = statement.getResultSet();
//            while (results.next())
//            {
//                System.out.println(results.getString("name"));
//            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (Exception ex)
            {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                statement.close();
            }
            catch (Exception ex)
            {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                results.close();
            }
            catch (Exception ex)
            {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
