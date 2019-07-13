package reverseabridged;

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
     * creates a reversed abridged output .fasta file
     * @param seq is the list of reversed sequences from input file
     */
    private void createAbridgedFasta(List<String> revSeqList)
    {
        int count = 1;
        BufferedWriter bufferedWriter = null;
        try 
        {
            File outFile = new File("C:\\Users\\abiaps\\Downloads\\dummyout.fasta");
            if (!outFile.exists()) 
            {
                outFile.createNewFile();
            }
            Writer writer = new FileWriter(outFile);
            bufferedWriter = new BufferedWriter(writer);
            StringBuilder writeStr = new StringBuilder();
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
                count++;
            }
            bufferedWriter.write(writeStr.toString());
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
            }
        }
    }

    public static void main(String[] args) 
    {
        ReverseAbridged revAbr = new ReverseAbridged();
        String inFile = "C:\\Users\\abiaps\\Downloads\\dummy.fasta";
	try
        {            
            BufferedReader inputFile = new BufferedReader( new FileReader(inFile));
            StringBuilder   sb = new StringBuilder();
            String line = inputFile.readLine();
            List<String> seq = new ArrayList<>();
            if( line == null )
                throw new IOException( inFile + " is an empty file" );
            if( line.charAt( 0 ) != '>' )
                throw new IOException( "First line of " + inFile + " should start with '>'" );
            else
            {
                for( line = inputFile.readLine().trim(); line != null; line = inputFile.readLine() )
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
            // write to file
            revAbr.createAbridgedFasta(seq);
      }
      catch(IOException e)
      {
        System.out.println("Error when reading " + inFile);
        e.printStackTrace();
      }
    }    
}
