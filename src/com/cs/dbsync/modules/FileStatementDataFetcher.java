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
public abstract class FileStatementDataFetcher implements DbDataFetcher
{
    private String statement;

    public FileStatementDataFetcher(String queryFilePath) throws IOException
    {
//        this.statement = FileUtils.readWholeFile(this.getClass().getResourceAsStream(queryFilePath));
        this.statement = FileUtils.readWholeFile(new FileInputStream(System.getProperty("user.dir") + "/queries/" + queryFilePath));
    }

    public String getStatement()
    {
        return statement;
    }
}
