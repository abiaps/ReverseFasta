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
import java.util.stream.Collectors;

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
            
            List<String> res = new ArrayList<>();

            for (String seq : revSeqList) 
            {
                if(seq.equals("invalid sequence"))
                {
                    count++;
                    continue;
                }                
                res.add(">REV_"+ count + " reversed");                
                if(seq.length() > 80)
                {
                   int endInd = 80, stInd = 0;
                   while(endInd < seq.length())
                   {
                       res.add(seq.substring(stInd, endInd));
                       stInd = endInd;
                       endInd += 80;
                   }
                   if(stInd < seq.length())
                   {
                        res.add(seq.substring(stInd));
                   }                       
                }
                else
                {
                    res.add(seq);
                }
                count++;
            }
            for(String l: res)
            {
                bufferedWriter.write(l+"\n");
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
            if( !line.isEmpty() && line.charAt( 0 ) != '>' )
            {
                seq.add("invalid sequence");
                while(line != null && !line.isEmpty() && line.trim().charAt(0) != '>')
                {
                    line = bufReader.readLine();// go to the 1st valid desc line
                }
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
                        seq.add("invalid sequence");
                        sb = new StringBuilder();
                        // see if the rest of the file contain valid seq, find next > 
                        while(currentLine.charAt(0) != '>')
                        {
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
        revAbr.getReversedFasta("resources/input/dummyInvalid1.fasta","resources/output/dummyInvalidOut1.fasta");
        revAbr.getReversedFasta("resources/input/dummyEmpty.fasta","resources/output/dummyEmptyOut.fasta");
        revAbr.getReversedFasta("resources/input/dummyInvalid.fasta","resources/output/dummyInvalidOut.fasta");
        revAbr.getReversedFasta("resources/input/dummyHuge.fasta","resources/output/dummyHugeOut.fasta");
        revAbr.getReversedFasta("resources/input/dummy.fasta", "resources/output/dummyOut.fasta");
    }    
}
