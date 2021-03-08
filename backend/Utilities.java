package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities
{
    public static ArrayList<String> readFile(String path) throws IOException
    {
        ArrayList<String> l = new ArrayList<String>();
        
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line != null)
        {
            l.add(line);
            line = br.readLine();
        }
        
        br.close();
        
        return l;
    }
    
    public static int letterToInt(char c)
    {
        // 'A' maps to 0
        return (int)c - (int)'A';
    }
    
    public static int numCharToInt(char n)
    {
        return (int)n - (int)'1';
    }
}
