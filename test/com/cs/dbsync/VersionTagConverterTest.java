/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author m.briedl
 */
public class VersionTagConverterTest
{
    @Test
    public void testApply()
    {
        VersionTagConverter c = new VersionTagConverter();
        Assert.assertEquals(0, (long) c.apply("0.0"));
        Assert.assertEquals(0, (long) c.apply("0.0.0.0"));
        Assert.assertEquals(6000237000L, (long) c.apply("6.0.237.0"));
        Assert.assertEquals(5000949000L, (long) c.apply("5.949"));
    }
}
