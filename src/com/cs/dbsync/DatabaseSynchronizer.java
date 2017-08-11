/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync;

import com.cs.dbsync.modules.CategoryFileStatementDataFetcher;
import com.cs.dbsync.modules.DbDataPusher;
import com.cs.dbsync.modules.LocalDbDataFetcher;
import com.cs.dbsync.modules.RemoteDbDataPusher;
import java.sql.Connection;
import com.cs.dbsync.modules.DbDataFetcher;
import com.cs.dbsync.modules.LocalDbDataUpdater;
import com.cs.dbsync.modules.RemoteCategoriesDbDataFetcher;
import com.cs.dbsync.modules.RemoteDuplicateDbDataDeleter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class DatabaseSynchronizer
{
    public void syncDBs(Connection localDb, Connection remoteDb) throws Exception
    {
        System.out.println("Starting synchronization...\n\n");
        long atStart = System.currentTimeMillis();
        
        DbDataFetcher remoteCategoriesFetcher = new RemoteCategoriesDbDataFetcher();
        DbDataFetcher localFetcher = new LocalDbDataFetcher();
        DbDataPusher remoteDeleter = new RemoteDuplicateDbDataDeleter().setDbConnection(remoteDb);
        DbDataPusher remotePusher = new RemoteDbDataPusher().setDbConnection(remoteDb);
        DbDataPusher localUpdater = new LocalDbDataUpdater().setDbConnection(localDb);
        
        ResultSet categories = null;
        try
        {
            categories = remoteCategoriesFetcher.loadData(remoteDb);
            while(categories.next())
            {
                syncCategory(categories.getString("cs_match"), (CategoryFileStatementDataFetcher) localFetcher, remoteDeleter, remotePusher, localUpdater, localDb, remoteDb);
                System.out.println("\n");
            }
        }
        finally
        {
            System.out.println("Finished synchronization (Total time: " + Math.abs(System.currentTimeMillis() - atStart) + "ms)");
            try
            {
                categories.close();
            }
            catch(Exception e)
            {
                Logger.getLogger(DatabaseSynchronizer.class.getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
    
    private void syncCategory(String category,
            CategoryFileStatementDataFetcher localFetcher,
            DbDataPusher remoteDeleter,
            DbDataPusher remotePusher,
            DbDataPusher localUpdater,
            Connection localDb, Connection remoteDb) throws Exception
    {
        ResultSet data = null;
        try
        {
            data = ((LocalDbDataFetcher) localFetcher).setCategory(category).loadData(localDb);
            while(data.next())
            {
                remoteDeleter.handleData(data);
                remotePusher.handleData(data);
                localUpdater.handleData(data);
            }
        }
        catch(Exception e)
        {
            System.out.println("Ungraceful exit - " + e.getMessage());
            throw e;
        }
        finally
        {
            System.out.println("Finished for category " + category);
            System.out.println("Rows deleted from " + remoteDb.getMetaData().getDatabaseProductName() + " -> news_articles: " + remoteDeleter.getAffectedRows());
            System.out.println("Rows inserted into " + remoteDb.getMetaData().getDatabaseProductName() + " -> news_articles: " + remotePusher.getAffectedRows());
            System.out.println("Rows updated in " + localDb.getMetaData().getDatabaseProductName() + " -> news: " + localUpdater.getAffectedRows());
            
            remoteDeleter.resetAffectedRows();
            remotePusher.resetAffectedRows();
            localUpdater.resetAffectedRows();
            try
            {
                data.close();
            }
            catch(Exception e)
            {
                Logger.getLogger(DatabaseSynchronizer.class.getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
}
