/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService;

/**
 *
 * @author nathanvandalen
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

public class PubMedConfiguration extends Configuration {
//<editor-fold defaultstate="collapsed" desc="YAML Properties">
    @NotEmpty
    private String healthCheck;
    @NotEmpty
    private final String defaultName = "Stranger";
    @NotEmpty
    private String serviceName;   
    @NotEmpty
    private String serviceIp;    
    @NotEmpty
    private String dbIp;  
    @NotEmpty
    private String dbPort;   
    @NotEmpty
    private String dbName;
    @NotEmpty
    private String dbUser;
    @NotEmpty
    private String dbPw;
//</editor-fold>
    /**
     * @return healthCheck
     */
    @JsonProperty
    public String getHealthCheck() {
        return healthCheck;
    }
    /**
     * @return defaultName
     */
    public String getDefaultName() {
        return defaultName;
    }
    /**
     * @return the serviceIp
     */
    @JsonProperty
    public String getServiceIp() {
        return serviceIp;
    }
    /**
     * @return the serviceName
     */
    @JsonProperty
    public String getServiceName() {
        return serviceName;
    }
    /**
     * @return the DatabaseIP
     */
    @JsonProperty
    public String getDbIp() {
        return dbIp;
    }
    /**
     * @return the DBPort
     */
    @JsonProperty
    public String getDbPort() {
        return dbPort;
    }
    /**
     * @return the dbName
     */
    @JsonProperty
    public String getDbName() {
        return dbName;
    }
    /**
     * @return the dbUser
     */
    public String getDbUser() {
        return dbUser;
    }
    /**
     * @return the dbPw
     */
    public String getDbPw() {
        return dbPw;
    }

}
