/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.output;

/**
 *
 * @author nathanvandalen
 */
public class JsonString {
    protected String label;
    protected String data;
    public JsonString(String label, String data){
        setLabel(label);
        setData(data);
    }
    /**
     * 
     * @return 
     */
    public String getJsonString(){
        return "\t\t\""+getLabel()+"\": \""+getData()+"\"";
    }
    /**
     * @return the label
     */
    private String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    private void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the data
     */
    private String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    private void setData(String data) {
        this.data = data;
    }
}
