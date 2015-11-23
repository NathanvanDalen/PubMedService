/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService.resources;

/**
 *
 * @author nathanvandalen
 */
//<editor-fold defaultstate="collapsed" desc="Imports">
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.han.biocentre.PubMedService.output.JsonCreator;
import com.han.biocentre.PubMedService.core.Saying;
import com.han.biocentre.PubMedService.database.SqlDatabaseConnection;
import com.han.biocentre.PubMedService.database.DatabaseUrlCreator;
import com.han.biocentre.PubMedService.database.ExpressionBuilder;
import com.han.biocentre.PubMedService.output.JsonColumnString;
import com.han.biocentre.PubMedService.output.JsonResultsString;
import com.han.biocentre.PubMedService.output.Output;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
//</editor-fold>

@Path("/PubMed")
public class PubMedResource {

    private final String healthCheckTemplate;
    private final String defaultName;
    private final AtomicLong counter;
    private final String serviceIp;
    private final String dbIp;
    private final String dbPort;
    private final String dbName;
    private final String dbUser;
    private final String dbPw;
    private ExpressionBuilder expression;

    /**
     *
     * @param healthCheckTemplate
     * @param defaultName
     * @param serviceIp
     * @param dbIp
     * @param dbPort
     * @param dbName
     * @param dbUser
     * @param dbPw
     */
    public PubMedResource(String healthCheckTemplate, String defaultName, String serviceIp, String dbIp, String dbPort, String dbName, String dbUser, String dbPw) {
        this.healthCheckTemplate = healthCheckTemplate;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.serviceIp = serviceIp;
        this.dbIp = dbIp;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPw = dbPw;
    }

    /**
     * This will be our publicAPI. It will be accesible by serviceIp+@Path.
     * Output should be in Json format
     *
     * @param asyncResponse
     * @param columns
     * @param tables
     * @param condition
     */
    @GET
    @Path("/search")
    @Timed
    @Produces({MediaType.TEXT_PLAIN})
    public void getData(@Suspended final AsyncResponse asyncResponse, @QueryParam("columns") final Optional<String> columns, @QueryParam("tables") final Optional<String> tables, @QueryParam("condition") final Optional<String> condition) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = veryExpensiveOperation();
                asyncResponse.resume(result);
            }

            private String veryExpensiveOperation() {
                Connection con = null;
                Statement st = null;
                ResultSet rs = null;
                ResultSetMetaData md = null;
                ArrayList columnsArrayList = paramDataToArrayList(columns.get());
                ArrayList tablesArrayList = paramDataToArrayList(tables.get());
                String clause = condition.get().replace('_', ' ');
                String output = "";
                String databaseUrl = new DatabaseUrlCreator(dbIp, dbPort, dbName).getURL();
                SqlDatabaseConnection DB = new SqlDatabaseConnection(databaseUrl, dbUser, dbPw);
                expression = new ExpressionBuilder(dbName, columnsArrayList, tablesArrayList, clause);
        //"SELECT PMID, Compound FROM PubMed WHERE "+statementWhere;

                try {
                    con = DB.Connect();
                    st = con.createStatement();
                    rs = st.executeQuery(expression.getExpression());
                    md = rs.getMetaData();
                    int nCol = md.getColumnCount();
                    List<String[]> table = new ArrayList<>();
                    while (rs.next()) {
                        String[] row = new String[nCol];
                        for (int iCol = 1; iCol <= nCol; iCol++) {
                            row[iCol - 1] = rs.getObject(iCol).toString();
                        }
                        table.add(row);
                    }
                    output = createResultsString(md, rotateCW(table), columns.get());

                } catch (SQLException ex) {
                    Logger.getLogger(PubMedResource.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (st != null) {
                            st.close();
                        }
                        if (con != null) {
                            con.close();
                        }

                    } catch (SQLException ex) {
                        Logger lgr = Logger.getLogger(PubMedResource.class.getName());
                        lgr.log(Level.WARNING, ex.getMessage(), ex);
                    }
                }
                return output;
            }
        }).start();

    }

    private ArrayList paramDataToArrayList(String paramData) {
        ArrayList paramDataList;
        paramDataList = new ArrayList(Arrays.asList(paramData.split(",")));
        return paramDataList;
    }

    static String[][] rotateCW(List<String[]> mat) {
        int w = mat.size();
        int h = mat.get(0).length;
        String[][] ret = new String[h][w];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                ret[i][j] = mat.get(j)[h - i - 1];
            }
        }
        return ret;
    }

    private String createResultsString(ResultSetMetaData md, String[][] table, String columns) throws SQLException {
        String jsonResults = null;
        int cDL = table.length;
        String[] columnData = new String[cDL];
        ArrayList columnNames = paramDataToArrayList(columns);
        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i).toString();
            JsonColumnString js2 = new JsonColumnString(columnName, table[i]);
            columnData[i] = js2.getJsonString();
        }
        jsonResults = new JsonResultsString(columnData).getJsonStringResults();
        Output jsonOut = new Output(dbName, expression.getExpression(), jsonResults);
        return jsonOut.getJsonOutput();
    }
}
