/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.modules;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author m.briedl
 */
public interface DbDataFetcher
{
    ResultSet loadData(Connection dbConnection) throws Exception;
}
