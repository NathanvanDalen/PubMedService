/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.voorbeeldmicroservice.databaseTests;

import com.han.biocentre.PubMedService.database.ExpressionBuilder;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nathanvandalen
 */
public class CreateExpressionTest {
    ArrayList tables = new ArrayList();
    ArrayList columns = new ArrayList();
    ExpressionBuilder st;
    String preMadeStatement1 = "SELECT PMID, Compound FROM PubMed WHERE Compound = 'Zinc'";
    String preMadeStatement2 = "SELECT column1, column2, column3 FROM table1, table2 WHERE Compound = 'Zinc'";
        
    public CreateExpressionTest() {
        
    }
    
    @Test
    public void getStatementTest1(){
        tables.add("PubMed");
        columns.add("PMID");
        columns.add("Compound");
        st = new ExpressionBuilder("localPubMed",columns,tables,"Compound = 'Zinc'");
    
        assertEquals(preMadeStatement1,st.getExpression());
    }
    @Test
    public void getStatementTest2(){
        tables.add("table1");
        tables.add("table2");
        columns.add("column1");
        columns.add("column2");
        columns.add("column3");
        st = new ExpressionBuilder("localPubMed",columns,tables,"Compound = 'Zinc'");
    
        assertEquals(preMadeStatement2,st.getExpression());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
