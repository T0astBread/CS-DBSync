/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author m.briedl
 */
public class RemoteCategoriesDbDataFetcher extends FileStatementDataFetcher
{
    public RemoteCategoriesDbDataFetcher() throws IOException
    {
//        super("../queries/RemoteCategoriesDbDataFetcherQuery.sql");
        super("RemoteCategoriesDbDataFetcherQuery.sql");
    }
    
    @Override
    public ResultSet loadData(Connection dbConnection) throws Exception
    {
        Statement statement = dbConnection.createStatement();
        statement.execute(getStatement());
        return statement.getResultSet();
    }
}
