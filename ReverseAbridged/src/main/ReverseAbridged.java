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
    private File createAbridgedFasta(List<String> revSeqList)
    {
        int count = 1;
        BufferedWriter bufferedWriter = null;
        String filePath = "resources/output/dummyout.fasta";
        File outFile = new File(filePath);
        try 
        {            
            if (!outFile.exists()) 
            {
                outFile.createNewFile();
            }
            Writer writer = new FileWriter(outFile);
            bufferedWriter = new BufferedWriter(writer);
            StringBuilder writeStr = new StringBuilder();
            if(revSeqList.isEmpty())
            {
                bufferedWriter.write("Empty input file!");
                return outFile;
            }
            if(revSeqList.size() > 0 && revSeqList.get(revSeqList.size()-1).equals("Invalid fasta format!"))
            {
                bufferedWriter.write(revSeqList.get(revSeqList.size()-1).trim());
                return outFile;
            }
            for (String seq : revSeqList) 
            {
                writeStr.append(">REV_").append(count).append(" reversed\n");
                if(seq.length() > 80)
                {
                   int endInd = 80, stInd = 0;
                   while(endInd < seq.length())
                   {
                       writeStr.append(seq.substring(stInd, endInd)).append("\n");
                       stInd = endInd;
                       endInd += 80;
                   }
                   if(stInd < seq.length())
                   {
                       writeStr.append(seq.substring(stInd)).append("\n");
                   }                       
                }
                else
                {
                    writeStr.append(seq).append("\n");
                }
                count++;
            }
            bufferedWriter.write(writeStr.toString().trim());
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
            if( line.charAt( 0 ) != '>' )
            {
                System.out.println( "First line of " + inFile + " should start with '>'" );
                seq.add("Invalid fasta format!");  
                return seq;
            }                
            else
            {
                for( line = bufReader.readLine().trim(); line != null; line = bufReader.readLine() )
                {
                    if( line.length()>0 && line.charAt( 0 ) == '>' )
                    {                       
                        if(sb.length() > 0)
                        {
                            seq.add(sb.reverse().toString());
                            sb = new StringBuilder();
                        }                            
                    } 
                    else 
                    {
                        sb.append(line.trim());
                    }                    
                }
                if(sb.length() != 0)
                {
                    seq.add(sb.reverse().toString());
                }                        
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
    
    public File getReversedFasta(String inFile)
    {
        List<String> seq = readInputFasta(inFile);
        return createAbridgedFasta(seq);
    }
    
    public static void main(String[] args) 
    {
        ReverseAbridged revAbr = new ReverseAbridged();        
        revAbr.getReversedFasta("resources/input/dummy.fasta");
    }    
}
