/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.voorbeeldmicroservice.JSONTests;

import com.han.biocentre.PubMedService.output.JsonColumnString;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nathanvandalen
 */
public class ConvertToJsonStringTest {
    private String expectedOutput;
    private JsonColumnString js ;
    public ConvertToJsonStringTest() {
        
    }
    
    @Test
    public void getJsonString1(){
        expectedOutput = "\"PMIDS\": [\"26050091\", \"26096734\", \"26216012\"]";
        String[] pmids;
        pmids = new String[3];
        pmids[0] = "26050091";
        pmids[1] = "26096734";
        pmids[2] = "26216012";
        js = new JsonColumnString("PMIDS",pmids);
        assertEquals(null, expectedOutput, js.getJsonString());
    }
    
    @Test
    public void getJsonString2(){
        expectedOutput = "\"Compounds\": [\"Zinc\", \"Zinc\", \"Zinc\"]";
        String[] compounds;
        compounds = new String[3];
        compounds[0] = "Zinc";
        compounds[1] = "Zinc";
        compounds[2] = "Zinc";
        js = new JsonColumnString("Compounds",compounds);
        assertEquals(null, expectedOutput, js.getJsonString());
    }
    
}
