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
import reverseabridged.ReverseAbridged;

/**
 *
 * @author abiaps
 */
public class ReverseAbridgedJUnitTest {
    
    public ReverseAbridgedJUnitTest() 
    {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
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
     
//     @Test
//     public void complexTest() 
//     {
//        try {
//            ReverseAbridged rab = new ReverseAbridged();
//            File outputFile = rab.getReversedFasta("C:\\Users\\abiaps\\Downloads\\dummy.fasta");
//            File expectedFile = new File("C:\\Users\\abiaps\\Downloads\\dummyExp.fasta");
//            assertEquals(FileUtils.readLines(outputFile), FileUtils.readLines(expectedFile));
//        } 
//        catch (IOException ex) {
//            Logger.getLogger(ReverseAbridgedJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     }
}
