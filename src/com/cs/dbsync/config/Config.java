/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.dbsync.config;

/**
 *
 * @author m.briedl
 */
public class Config
{
    private ServerConfig local, remote;

    public Config(ServerConfig local, ServerConfig remote)
    {
        this.local = local;
        this.remote = remote;
    }

    public ServerConfig getLocal()
    {
        return local;
    }

    public ServerConfig getRemote()
    {
        return remote;
    }
}
