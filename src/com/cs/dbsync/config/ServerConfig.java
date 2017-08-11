/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.config;

import com.cs.dbsync.utils.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class ServerConfig
{
    private String dbSystem, host, dbName, username, password;
    private int port;

    public ServerConfig(String dbSystem, String host, int port, String dbName, String username, String password)
    {
        this.dbSystem = dbSystem;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }

    public String getDbSystem()
    {
        return dbSystem;
    }

    public String getHost()
    {
        return host;
    }

    public String getDbName()
    {
        return dbName;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        if (this.password == null)
        {
            try
            {
                System.out.println("Please enter the password for user " + this.username + " on " + this.host + "/" + this.dbName + ":");
                this.password = IOUtils.getInst().in.readLine();
                System.out.println();
            }
            catch (IOException ex)
            {
                this.password = "";
                Logger.getLogger(ServerConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return password;
    }

    public int getPort()
    {
        return port;
    }
}
