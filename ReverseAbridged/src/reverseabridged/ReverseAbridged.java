/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverseabridged;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abiaps
 */
public class ReverseAbridged {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            System.out.println(" seq size: " + seq.size());
            
      }
      catch(IOException e)
      {
        System.out.println("Error when reading "+file);
        e.printStackTrace();
      }
    }    
}
