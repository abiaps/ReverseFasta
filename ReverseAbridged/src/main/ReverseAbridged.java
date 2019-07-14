package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abiaps
 */
public class ReverseAbridged 
{
    /**
     * creates a reversed abridged output .fasta file and returns the path for output file
     * @param seq is the list of reversed sequences from input file
     */    
    private File createAbridgedFasta(List<String> revSeqList, String filePath)
    {
        int count = 1;
        BufferedWriter bufferedWriter = null;        
        File outFile = new File(filePath);   
        try 
        {            
            if (!outFile.exists()) 
            {
                outFile.createNewFile();
            }
            Writer writer = new FileWriter(outFile);
            bufferedWriter = new BufferedWriter(writer);
            if(revSeqList.isEmpty())
            {
                bufferedWriter.write("Empty input file!");
                return outFile;
            }
            if(revSeqList.size() == 1 && revSeqList.get(revSeqList.size()-1).equals("invalid sequence"))
            {
                return outFile;
            }            
            for (String seq : revSeqList) 
            {
                if(seq.equals("invalid sequence"))
                {
                    count++;
                    continue;
                }  
                bufferedWriter.write(">REV_"+ count + " reversed\n");
                if(seq.length() > 80)
                {
                   int endInd = 80, stInd = 0;
                   while(endInd < seq.length())
                   {
                       bufferedWriter.write(seq.substring(stInd, endInd)+"\n");
                       stInd = endInd;
                       endInd += 80;
                   }
                   if(stInd < seq.length())
                   {
                        bufferedWriter.write(seq.substring(stInd)+"\n");
                   }                       
                }
                else
                {
                    bufferedWriter.write(seq+"\n");
                }
                count++;
            }      
        } 
        catch (IOException e) 
        {           
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(bufferedWriter != null) bufferedWriter.close();
            } 
            catch(Exception ex){ 
                    // do nothing
            }
        }
        return outFile;
    }
    
    private List<String> readInputFasta(String inFile)
    {        
        List<String> seq = new ArrayList<>();
        BufferedReader bufReader = null;
	try
        {            
            bufReader = new BufferedReader( new FileReader(inFile));
            StringBuilder   sb = new StringBuilder();
            String line = bufReader.readLine();
           
            if( line == null )
            {
                System.out.println( inFile + " is an empty file" );                
                return seq;
            } 
            while(line != null && !line.isEmpty() && line.trim().charAt(0) != '>')
            {
                if(line.contains(" "))
                {
                    seq.add("invalid sequence");
                }                        
                line = bufReader.readLine();// go to the next valid desc line                    
            }            
            
            for( String currentLine = line; currentLine != null && !currentLine.isEmpty(); currentLine = bufReader.readLine() )
            {
                currentLine = currentLine.trim();
                if( currentLine.length() > 0 && currentLine.charAt( 0 ) == '>' )
                {                       
                    if(sb.length() > 0)
                    {
                        seq.add(sb.reverse().toString());
                        sb = new StringBuilder();
                    }                            
                } 
                else 
                {
                    if(currentLine.contains(" "))     // a sequence won't contain whitespace
                    {
                        // description or corrupt sequence without > or sequence contains space
                        if(sb.length() > 0)
                        {
                            seq.add(sb.reverse().toString());
                        }
                        sb = new StringBuilder();
                        // see if the rest of the file contain valid seq, find next > 
                        while(currentLine != null && currentLine.length() > 0 && currentLine.charAt(0) != '>')
                        {
                            if(currentLine.contains(" "))
                                seq.add("invalid sequence");
                            currentLine = bufReader.readLine();                            
                        }
                    }
                    else
                    {
                        sb.append(currentLine);
                    }
                }                    
            }
            if(sb.length() != 0)
            {
                seq.add(sb.reverse().toString());
            }   
      }
      catch(IOException e)
      {
        System.out.println("Error when reading " + inFile);
        e.printStackTrace();
      }
      finally
        {
            try
            {
                if(bufReader != null) bufReader.close();
            } 
            catch(Exception ex){                 
            }
        }
      return seq; 
    }
        
    public File getReversedFasta(String inFile, String outFile)
    {
        List<String> seq = readInputFasta(inFile);
        return createAbridgedFasta(seq, outFile);
    }
        
    public static void main(String[] args) 
    {
        ReverseAbridged revAbr = new ReverseAbridged();        
        revAbr.getReversedFasta("resources/input/dummy.fasta", "resources/output/dummyOut.fasta");
        revAbr.getReversedFasta("resources/input/dummyEmpty.fasta","resources/output/dummyEmptyOut.fasta");
        revAbr.getReversedFasta("resources/input/dummyInvalid.fasta","resources/output/dummyInvalidOut.fasta");
        revAbr.getReversedFasta("resources/input/dummyInvalid1.fasta","resources/output/dummyInvalidOut1.fasta");
        revAbr.getReversedFasta("resources/input/dummyHuge.fasta","resources/output/dummyHugeOut.fasta");
        revAbr.getReversedFasta("resources/input/dummy1.fasta", "resources/output/dummyOut1.fasta");
        revAbr.getReversedFasta("resources/input/dummy2.fasta", "resources/output/dummyOut2.fasta");
        revAbr.getReversedFasta("resources/input/dummy3.fasta", "resources/output/dummyOut3.fasta");
        revAbr.getReversedFasta("resources/input/dummy4.fasta", "resources/output/dummyOut4.fasta");
        revAbr.getReversedFasta("resources/input/dummy5.fasta", "resources/output/dummyOut5.fasta");
    }    
}
