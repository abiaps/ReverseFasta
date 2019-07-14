package main;

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
            File outputFile = rab.getReversedFasta("resources/input/dummy.fasta");
            File expectedFile = new File("resources/expected/dummyExp.fasta");
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
            File outputFile = rab.getReversedFasta("resources/input/dummyEmpty.fasta");
            File expectedFile = new File("resources/expected/dummyEmptyExp.fasta");
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
            File outputFile = rab.getReversedFasta("resources/input/dummyInvalid.fasta");
            File expectedFile = new File("resources/expected/dummyInvalidExp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
