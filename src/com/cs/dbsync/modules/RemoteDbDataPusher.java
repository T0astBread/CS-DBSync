/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import com.cs.dbsync.VersionTagConverter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.briedl
 */
public class RemoteDbDataPusher extends FileStatementDbDataPusher
{
    private final VersionTagConverter versionTagConverter;

    public RemoteDbDataPusher() throws IOException
    {
//        super("../queries/RemoteDbDataPusherQuery.sql");
        super("/RemoteDbDataPusherQuery.sql");
        this.versionTagConverter = new VersionTagConverter();
    }
    
    @Override
    public void pushData(ResultSet data, Connection dbConnection) throws Exception
    {
        PreparedStatement insert1 = null;
        PreparedStatement insert2 = null;
        try
        {
            String[] statementParts = getStatement().split("STATEMENT-SEPERATOR");
            
            insert1 = dbConnection.prepareStatement(statementParts[0] + "*/");
            Date date = data.getDate("Datum");
            if(date == null) date = Date.valueOf(LocalDate.now());
            insert1.setDate(1, date);
            insert1.setString(2, data.getString("Prg"));
            insert1.setLong(3, this.versionTagConverter.apply(data.getString("VNR")));
            insert1.setBoolean(4, true);
            insert1.setInt(5, data.getInt("ID"));
//            System.out.println("Inserted " + insert1.executeUpdate() + " rows into news_articles (news article meta data) table...");
            incrementAffectedRows(insert1.executeUpdate());

            insert2 = dbConnection.prepareStatement("/*" + statementParts[1]);
            insert2.setString(1, data.getString("Ueberschrift_unfor"));
            insert2.setString(2, data.getString("Beschreibung_unfor"));
//            System.out.println("Inserted " + insert2.executeUpdate() + " rows into news_article_localizations table...");
            incrementAffectedRows(insert2.executeUpdate());
        }
        finally
        {
            try
            {
                insert1.close();
            }
            catch(Exception e)
            {
                Logger.getLogger(RemoteDbDataPusher.class.getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
}
