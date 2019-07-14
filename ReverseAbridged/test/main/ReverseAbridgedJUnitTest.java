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
      * contains one seq with description not starting with >
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
      * this input file contains two invalid sequences in between
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
      * this is a stress test to run 896177 lines of fasta file
      * this file is taken from ftp://ftp.ncbi.nlm.nih.gov/genomes/Cricetulus_griseus/protein/
      */
     @Test
     public void hugeFileTest() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummyHuge.fasta","resources/output/dummyHugeOut.fasta");
            assertNotNull(FileUtils.readLines(outputFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests non-empty files with intermediate invalid sequences
     */
     @Test
     public void simpleTestOne() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummy1.fasta", "resources/output/dummyOut1.fasta");
            File expectedFile = new File("resources/expected/dummyExp1.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests non-empty files with intermediate invalid sequences (another variation)
     */
     @Test
     public void simpleTestTwo() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummy2.fasta", "resources/output/dummyOut2.fasta");
            File expectedFile = new File("resources/expected/dummyExp2.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests non-empty files with intermediate invalid sequences (another variation)
     */
     @Test
     public void simpleTestThree() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummy3.fasta", "resources/output/dummyOut3.fasta");
            File expectedFile = new File("resources/expected/dummyExp3.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests non-empty files with intermediate invalid sequences (another variation)
     */
     @Test
     public void simpleTestFour() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummy4.fasta", "resources/output/dummyOut4.fasta");
            File expectedFile = new File("resources/expected/dummyExp4.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * tests non-empty files with intermediate invalid sequences (another variation)
     */
     @Test
     public void simpleTestFive() 
     {
        try {
            ReverseAbridged rab = new ReverseAbridged();
            File outputFile = rab.getReversedFasta("resources/input/dummy5.fasta", "resources/output/dummyOut5.fasta");
            File expectedFile = new File("resources/expected/dummyExp5.fasta");
            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
        } 
        catch (IOException ex) {
            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
