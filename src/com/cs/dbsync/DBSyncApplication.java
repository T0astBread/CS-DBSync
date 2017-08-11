/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync;

import com.cs.dbsync.config.Config;
import com.cs.dbsync.config.ServerConfig;
import com.cs.dbsync.connection.DbConnectionEstablisher;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class DBSyncApplication
{
    private Config config;
    private DatabaseSynchronizer synchronizer;

    public DBSyncApplication()
    {
        try
        {
            this.config = loadConfig();
            this.synchronizer = new DatabaseSynchronizer();
        }
        catch (IOException ex)
        {
            Logger.getLogger(DBSyncApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }
    
    private Config loadConfig() throws IOException
    {
        Gson gson = new Gson();
        try(FileReader confReader = new FileReader("dbsync-config.json"))
        {
            return gson.fromJson(confReader, Config.class);
        }
    }
    
    public void start()
    {
        System.out.println();
        System.out.println("==================");
        System.out.println("      DBSync      ");
        System.out.println("==================\n\n");
        Connection localDb = null;
        Connection remoteDb = null;
        try
        {
            DbConnectionEstablisher connectionEstablisher = new DbConnectionEstablisher();
            ServerConfig c = this.config.getLocal();
            localDb = connectionEstablisher.establishConnection(c.getDbSystem(), c.getHost(), c.getPort(), c.getDbName(), c.getUsername(), c.getPassword());
//            remoteDb = connectionEstablisher.establishConnection("MySQL", "localhost", 3306, "cs-website-test", "root", "");
            c = this.config.getRemote();
            remoteDb = connectionEstablisher.establishConnection(c.getDbSystem(), c.getHost(), c.getPort(), c.getDbName(), c.getUsername(), c.getPassword());
            this.synchronizer.syncDBs(localDb, remoteDb);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Logger.getLogger(DBSyncApplication.class.getName()).log(Level.SEVERE, e.toString());
        }
        finally
        {
            try
            {
                localDb.close();
            }
            catch (Exception e)
            {
                Logger.getLogger(DBSyncApplication.class.getName()).log(Level.SEVERE, e.toString());
            }
            try
            {
                remoteDb.close();
            }
            catch (Exception e)
            {
                Logger.getLogger(DBSyncApplication.class.getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
}
