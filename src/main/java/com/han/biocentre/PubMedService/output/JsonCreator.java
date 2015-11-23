/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.output;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author nathanvandalen
 */
public class JsonCreator {

    protected final ResultSet rs;
    protected final String dbName;
    protected final String query;
    protected String results;

    public JsonCreator(String dbName, String query, ResultSet rs) {
        this.dbName = dbName;
        this.query = query;
        this.rs = rs;
    }

    private void createJsonOutputFile(String jsonString) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output.json"), "utf-8"))) {
            writer.write(jsonString);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JsonCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String createJsonFromResultSet() throws SQLException {
        ResultSet resultSet = getRs();
        String qs = getQuery();
        List<String> columnNames = getDatabaseColumnNames(resultSet);
        List<String> columnData = new ArrayList<>();
        List<String> columnJsonStrings = new ArrayList<>();
        
        for (int i = 0; i < columnNames.size(); i++) {
            columnJsonStrings.add(createColumnDataJsonString(columnNames.get(i), getColumnData(i+1)));
        }
        results = createResultsJsonString(columnJsonStrings);
        return results;
    }

    private List<String> getColumnData(int i) throws SQLException {
        List<String> columnData = new ArrayList<>();
        while (getRs().next()) {
            columnData.add("\""+getRs().getString(i)+"\"");
        }
        return columnData;
    }

    private List<String> getDatabaseColumnNames(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int totalColumns = rsmd.getColumnCount();
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < totalColumns; i++) {
            columnNames.add(rsmd.getColumnName(i));
        }
        return columnNames;
    }
    private String createColumnDataJsonString(String columnName, List<String> columnData){
        return "\""+columnName+"\": "+columnData;
    }
    private String createResultsJsonString(List<String> columnJsonString){
        String jsonResults = "\"results\": {\n";
        for (String columnJsonString1 : columnJsonString) {
            jsonResults += columnJsonString1;
        }
        jsonResults+="\n}";
        return jsonResults;
    }
    private void createJsonForOutput(String results) {
        String jsonString = new JSONObject()
                .put("output", new JSONObject()
                        .put("database", getDbName())
                        .put("query", getQuery())
                        .put("results", results)).toString();
    }

    /**
     * @return the rs
     */
    private ResultSet getRs() {
        return rs;
    }

    /**
     * @return the dbName
     */
    private String getDbName() {
        return dbName;
    }

    /**
     * @return the query
     */
    private String getQuery() {
        return query;
    }
    @Override
    public String toString(){
        return results;
    }
}
//int beginIndex = qs.lastIndexOf("SELECT");
//        int endIndex = qs.indexOf("FROM");
//        String[] columnNames = qs.substring(beginIndex, endIndex).replace(" ", "").split(",");
//        JSONObject jsonStringColumns = new JSONObject();
//        for (int i = 0; i < columnNames.length; i++) {
//            jsonStringColumns.append(qs, i);
//        }
//        String jsonString = new JSONObject()
//                  .put("results", new JSONObject()
//                          .put("database", getDbName())
//                          .put("query", getQuery())
//                          .put("results", results)).toString();
