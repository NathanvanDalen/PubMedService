/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.database;

import java.util.ArrayList;

/**
 *
 * @author nathanvandalen
 */
public class ExpressionBuilder {
//<editor-fold defaultstate="collapsed" desc="Attributes">

    protected String label;
    protected String database;
    protected ArrayList tables;
    protected ArrayList columns;
    protected String condition;
    protected String expression;

    /**
     * @return the label
     */
    private String getLabel() {
        return label;
    }

    /**
     * @return the database
     */
    private String getDatabase() {
        return database;
    }

    /**
     * @return the tables
     */
    private ArrayList getTables() {
        return tables;
    }

    /**
     *
     * @return the tables as String
     */
    private String getTablesString() {
        String tablesString = "";
        for (int i = 0; i < tables.size(); i++) {
            tablesString += tables.get(i) + ", ";
        }
        return tablesString;
    }

    /**
     * @return the columns
     */
    private ArrayList getColumns() {
        return columns;
    }

    /**
     * @return the columns as String
     */
    private String getColumnsString() {
        String columnsString = "";
        for (int i = 0; i < columns.size(); i++) {
            columnsString += columns.get(i) + ", ";
        }
        return columnsString;
    }

    /**
     * @return the Condition
     */
    private String getCondition() {
        return condition;
    }

    /**
     * @param Condition the Condition to set
     */
    private void setCondition(String Condition) {
        this.condition = condition;
    }

    /**
     * @param statement the expression to set
     */
    private void setStatement(String statement) {
        this.expression = statement;
    }
//</editor-fold>

    public ExpressionBuilder(String database, ArrayList columns, ArrayList tables, String condition) {
        this.label = "query";
        this.database = database;
        this.tables = tables;
        this.columns = columns;
        this.condition = condition;
    }

    private void createExpression() {
        String statementSelect = removeTrailingComma("SELECT " + getColumnsString());
        String statementFrom = removeTrailingComma("FROM " + getTablesString());
        String statementWhere = "WHERE " + getCondition();
        setStatement(statementSelect + statementFrom + statementWhere);
    }

    private static String removeTrailingComma(String str) {
        return str.substring(0, str.length() - 2) + " ";
    }

    /**
     * @return the expression
     */
    public String getExpression() {
        createExpression();
        return expression;
    }

}
