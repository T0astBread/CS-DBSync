/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author m.briedl
 */
public class VersionTagConverter implements Function<String, Long>
{
    @Override
    public Long apply(String t)
    {
        try
        {
            String[] tokens = t.split("\\.");
            if(tokens.length == 2) tokens = new String[]{tokens[0], "000", tokens[1], "000"};
            return Long.parseLong(Arrays.stream(tokens).map(to -> String.join("", Collections.nCopies(3 - to.length(), "0")) + to).collect(Collectors.joining()));
        }
        catch(Exception e)
        {
            return 0L;
        }
    }
}
