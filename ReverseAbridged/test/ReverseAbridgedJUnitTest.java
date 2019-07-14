/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import main.ReverseAbridged;

/**
 *
 * @author abiaps
 */
public class ReverseAbridgedJUnitTest {
    
    public ReverseAbridgedJUnitTest() 
    {
    }
    
    /**
     * tests non-empty files
     */
     @Test
     public void simpleTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("C:\\Users\\abiaps\\Downloads\\dummy2.fasta");
            File expectedFile = new File("C:\\Users\\abiaps\\Downloads\\dummy2Exp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests empty files
     */
     @Test
     public void emptyTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("C:\\Users\\abiaps\\Downloads\\dummyEmpty.fasta");
            File expectedFile = new File("C:\\Users\\abiaps\\Downloads\\dummyEmptyExp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
      * tests invalid file
      */
     @Test
     public void complexTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("C:\\Users\\abiaps\\Downloads\\dummyInvalid.fasta");
            File expectedFile = new File("C:\\Users\\abiaps\\Downloads\\dummyInvalidExp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
