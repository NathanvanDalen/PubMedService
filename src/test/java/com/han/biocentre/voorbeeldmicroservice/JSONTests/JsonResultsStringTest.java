/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.voorbeeldmicroservice.JSONTests;

import com.han.biocentre.PubMedService.output.JsonColumnString;
import com.han.biocentre.PubMedService.output.JsonResultsString;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nathanvandalen
 */
public class JsonResultsStringTest {
    JsonResultsString jrs;
    JsonColumnString js1;
    JsonColumnString js2;
    String[] columnData;
    String preMadeString ="\t\t\"results\": {\n\t\t\t"+
            "\"PMIDS\": [\"26050091\", \"26096734\", \"26216012\"],\n\t\t\t"+
            "\"Compounds\": [\"Zinc\", \"Zinc\", \"Zinc\"]\n\t\t}";
    public JsonResultsStringTest() {
        String[] pmids = new String[3];
        pmids[0] = "26050091";
        pmids[1] = "26096734";
        pmids[2] = "26216012";
        js1 = new JsonColumnString("PMIDS",pmids);
        String[] compounds;
        compounds = new String[3];
        compounds[0] = "Zinc";
        compounds[1] = "Zinc";
        compounds[2] = "Zinc";
        js2 = new JsonColumnString("Compounds",compounds);
        columnData = new String[]{js1.getJsonString(),js2.getJsonString()};
    }
    

    @Test
    public void getJsonResultStringTest(){
        jrs = new JsonResultsString(columnData);
        assertEquals(preMadeString, jrs.getJsonStringResults());
    }
}
