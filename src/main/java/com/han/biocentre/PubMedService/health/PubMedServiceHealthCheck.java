/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.health;

/**
 *
 * @author nathanvandalen
 */

import com.codahale.metrics.health.HealthCheck;

public class PubMedServiceHealthCheck extends HealthCheck {
    private final String PubMedService;
    private final String serviceName;

    public PubMedServiceHealthCheck(String template, String serviceName) {
        this.PubMedService = template;
        this.serviceName = serviceName;
    }
    /**
     * 
     * @return healthCheckResult 
     * @throws Exception 
     */
    @Override
    protected Result check() throws Exception {
        final String saying = String.format(PubMedService, "Admin", serviceName);
        if (!saying.contains("PubMedService")) {
            return Result.unhealthy("HealthCheck failed to complete succesfully.");
        }
        return Result.healthy();
    }
    
}
