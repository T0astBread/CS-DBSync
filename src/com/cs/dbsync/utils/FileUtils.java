/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author m.briedl
 */
public class FileUtils
{
    public static String readWholeFile(InputStream fileIn) throws IOException
    {
        try(BufferedReader bReader = new BufferedReader(new InputStreamReader(fileIn)))
        {
            StringBuilder fileContents = new StringBuilder();
            String line = null;
            while((line = bReader.readLine()) != null)
            {
                fileContents.append(line).append("\n");
            }
            return fileContents.toString();
        }
    }
}
