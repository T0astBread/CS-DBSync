/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import java.io.IOException;

/**
 *
 * @author m.briedl
 */
public abstract class CategoryFileStatementDataFetcher extends FileStatementDataFetcher
{
    private String category;

    public CategoryFileStatementDataFetcher(String queryFilePath) throws IOException
    {
        super(queryFilePath);
    }

    public String getCategory()
    {
        return category;
    }

    public CategoryFileStatementDataFetcher setCategory(String category)
    {
        this.category = category;
        return this;
    }
}
