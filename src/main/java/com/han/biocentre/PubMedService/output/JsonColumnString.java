/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nathanvandalen
 */
public class JsonColumnString {
    protected String label;
    protected String[] data;
    protected String jsonString;
    public JsonColumnString(String label, String[] data){
        this.label = label;
        this.data = data;
    }
    
    /**
     * @param jsonString the jsonString to set
     */
    private void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
    
    public String getJsonString(){
        convertToJsonString();
        return jsonString;
    }
    /**
     *
     * @param data
     * @return
     */
    private void convertToJsonString(){
        ArrayList<String> dAl = new ArrayList<>();
            for (String value:data) {
                dAl.add("\""+value+"\"");
            }
            
        
        setJsonString("\""+label+"\": "+dAl);
    }
    
}
