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

/**
 *
 * @author m.briedl
 */
public class LocalDbDataFetcher extends CategoryFileStatementDataFetcher
{
    public LocalDbDataFetcher() throws IOException
    {
//        super("../queries/LocalDbDataFetcherQuery.sql");
        super("LocalDbDataFetcherQuery.sql");
    }

    @Override
    public ResultSet loadData(Connection dbConnection) throws Exception
    {
        PreparedStatement statement = null;
        statement = dbConnection.prepareStatement(getStatement());
//        statement.setInt(1, 100);
        statement.setString(1, getCategory());
        statement.execute();
        return statement.getResultSet();
    }
}
