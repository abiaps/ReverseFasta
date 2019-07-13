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
    private void createAbridgedFasta(List<String> seq)
    {
        int count = 1;
        BufferedWriter bufferedWriter = null;
        try 
        {
            File myFile = new File("C:\\Users\\abiaps\\Downloads\\dummyout.fasta");
            if (!myFile.exists()) 
            {
                myFile.createNewFile();
            }
            Writer writer = new FileWriter(myFile);
            bufferedWriter = new BufferedWriter(writer);
            StringBuilder fullStr = new StringBuilder();
            for (String seq1 : seq) 
            {
                fullStr.append(">REV_").append(count).append(" reversed\n");
                if(seq1.length() > 80)
                {
                   int end = 80, st = 0;
                   while(end < seq1.length())
                   {
                       fullStr.append(seq1.substring(st, end)).append("\n");
                       st = end;
                       end += 80;
                   }
                   if(st < seq1.length())
                   {
                       fullStr.append(seq1.substring(st)).append("\n");
                   }                       
                }
                count++;
            }
            bufferedWriter.write(fullStr.toString());
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
        ReverseAbridged rab = new ReverseAbridged();
        String file = "C:\\Users\\abiaps\\Downloads\\dummy.fasta";
	try
        {            
            BufferedReader inputFile     = new BufferedReader( new FileReader( file ) );
            StringBuilder   sb = new StringBuilder();
            String         line   = inputFile.readLine();
            List<String> seq = new ArrayList<>();
            if( line == null )
                throw new IOException( file + " is an empty file" );
            if( line.charAt( 0 ) != '>' )
                throw new IOException( "First line of " + file + " should start with '>'" );
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
            rab.createAbridgedFasta(seq);
      }
      catch(IOException e)
      {
        System.out.println("Error when reading "+file);
        e.printStackTrace();
      }
    }    
}
