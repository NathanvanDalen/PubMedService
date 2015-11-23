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
public class JsonResultsString {
    private static final String label = "results";
    private final String[] columnDataStrings;
    protected String jsonStringResults;
    
    public JsonResultsString(String[] columns){
        this.columnDataStrings = columns;
        createResultsObject();
    }
    
    private void createResultsObject(){
        String joinedDataStrings = String.join(",\n\t\t\t", columnDataStrings);
        setJsonStringResults(joinedDataStrings);
    }

    /**
     * @return the jsonStringResults
     */
    public String getJsonStringResults() {
        return jsonStringResults;
    }

    /**
     * @param jsonStringResults the jsonStringResults to set
     */
    private void setJsonStringResults(String jsonStringResults) {
        this.jsonStringResults = "\t\t\""+label+"\": {\n\t\t\t"+jsonStringResults+"\n\t\t}";
    }
}
