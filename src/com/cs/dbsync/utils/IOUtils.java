/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author m.briedl
 */
public class IOUtils implements Closeable
{
    private static IOUtils instance;
    
    public final BufferedReader in;

    public IOUtils()
    {
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public static IOUtils getInst()
    {
        return instance != null ? instance : (instance = new IOUtils());
    }

    @Override
    public void close() throws IOException
    {
        this.in.close();
    }
}
