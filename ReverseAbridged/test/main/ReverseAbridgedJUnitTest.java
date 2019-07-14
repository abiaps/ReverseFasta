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
     * given input file with all valid sequences
     */
     @Test
     public void simpleTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummy.fasta", "resources/output/dummyOut.fasta");
            File expectedFile = new File("resources/expected/dummyExp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests empty files
     * input file is empty
     */
     @Test
     public void emptyTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummyEmpty.fasta","resources/output/dummyEmptyOut.fasta");
            File expectedFile = new File("resources/expected/dummyEmptyExp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
      * tests invalid file
      * an input file with only one invalid sequence
      * the header or description does not start with >
      */
     @Test
     public void invalidTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummyInvalid.fasta","resources/output/dummyInvalidOut.fasta");
            File expectedFile = new File("resources/expected/dummyInvalidExp.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
      * tests invalid file. 
      * skips invalid sequences
      * if the description does not start with >, skip the invalid sequence
      */
     @Test
     public void invalidTestOne() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummyInvalid1.fasta","resources/output/dummyInvalidOut1.fasta");
            File expectedFile = new File("resources/expected/dummyInvalidExp1.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
      * tests huge file. 
      */
//     @Test
//     public void hugeFileTest() 
//     {
//        try {
//            ReverseAbridged rab = new ReverseAbridged();
//            File outputFile = rab.getReversedFasta("resources/input/dummyHuge.fasta","resources/output/dummyHugeOut.fasta");
//            File expectedFile = new File("resources/expected/dummyHugeExp.fasta");
//            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
//        } 
//        catch (IOException ex) {
//            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     }
}
