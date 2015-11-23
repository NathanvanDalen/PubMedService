/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.database;

/**
 *
 * @author nathanvandalen
 */
public class DatabaseUrlCreator {
    private final String ip;
    private final String port;
    private final String dbName;
    
    public DatabaseUrlCreator(String ip, String port, String dbName){
        this.ip = ip;
        this.port = port;
        this. dbName = dbName;
    }

    /**
     * @return the ip
     */
    private String getIp() {
        return ip;
    }

    /**
     * @return the port
     */
    private String getPort() {
        return port;
    }

    /**
     * @return the dbName
     */
    private String getDbName() {
        return dbName;
    }
    public String getURL(){
        String url = "jdbc:mysql://";
        url+=getIp();
        url+=":";
        url+=getPort();
        url+="/";
        url+=getDbName();
        return url;
    }
}

