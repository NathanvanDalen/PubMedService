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
public class Output {
    protected String dbName;
    protected String expression;
    protected String results;
    protected String[] outputStringArray;
    private JsonString js1;
    private JsonString js2;
    
    public Output(String dbName, String expression, String results){
        this.dbName = dbName;
        this.expression = expression;
        this.results = results;
        createOutputStringArray();
    }
    private void createOutputStringArray(){
        js1 = new JsonString("database", getDbName());
        js2 = new JsonString("query", getExpression());
        this.outputStringArray=new String[]{js1.getJsonString(),js2.getJsonString(),getResults()};
        
    }
    
    /**
     * @return the dbName
     */
    private String getDbName() {
        return dbName;
    }

    /**
     * @return the expression
     */
    private String getExpression() {
        return expression;
    }

    /**
     * @return the results
     */
    private String getResults() {
        return results;
    }

    /**
     * @return the outputStringArray
     */
    private String[] getOutputStringArray() {
        return outputStringArray;
    }
    public String getJsonOutput(){
        JsonOutputCreator joc = new JsonOutputCreator(getOutputStringArray());
        return joc.getJsonOutput();
    }
}
