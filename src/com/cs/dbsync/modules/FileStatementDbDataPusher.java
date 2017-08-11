/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import com.cs.dbsync.utils.FileUtils;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author m.briedl
 */
public abstract class FileStatementDbDataPusher extends DbDataPusher
{
    private final String query;

    public FileStatementDbDataPusher(String queryFilePath) throws IOException
    {
//        this.query = FileUtils.readWholeFile(FileStatementDataFetcher.class.getResourceAsStream(queryFilePath));
        this.query = FileUtils.readWholeFile(new FileInputStream(System.getProperty("user.dir") + "/queries/" + queryFilePath));
    }

    public String getStatement()
    {
        return query;
    }
}
