package com.han.biocentre.voorbeeldmicroservice.databaseTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.han.biocentre.PubMedService.database.DatabaseUrlCreator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nathanvandalen
 */
public class DatabaseUrlCreatorTest {
    private final DatabaseUrlCreator dbUrlCreator;
    String databaseIp = "91.38.123.56";
    String dbPort = "74";
    String databaseName = "URLTest";
    
    public DatabaseUrlCreatorTest() {
        dbUrlCreator = new DatabaseUrlCreator(databaseIp, dbPort, databaseName);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void getUrlTest(){
        String url = dbUrlCreator.getURL();
        assertEquals("Testing URL Generation", "jdbc:mysql://91.38.123.56:74/URLTest", url);
    }
}
