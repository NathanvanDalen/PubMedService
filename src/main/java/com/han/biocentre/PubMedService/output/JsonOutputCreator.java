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
public class JsonOutputCreator {
    protected String jsonOutputString; 
    protected final String label =  "output";
    protected final String sep = ",\n";
    
    public JsonOutputCreator(String[] jsonOutputObjects){
        createJsonOutputString(jsonOutputObjects);
    }
    private void createJsonOutputString(String[] jsonOutputObjects){
        String jsonString = "{\n\t\""+label+"\": {\n";
        jsonString += jsonOutputObjects[0]+sep;
        jsonString += jsonOutputObjects[1]+sep;
        jsonString += jsonOutputObjects[2];
        jsonString += "\n\t}\n}";
        setJsonOutput(jsonString);
    }
    /**
     * @return the jsonOutputString
     */
    public String getJsonOutput() {
        return jsonOutputString;
    }

    /**
     * @param jsonOutput the jsonOutputString to set
     */
    private void setJsonOutput(String jsonOutputString) {
        
        this.jsonOutputString = jsonOutputString;
    }
}
