package gr.ntua.multimediatechnology;

import java.util.Scanner;

public class HumanPlayer extends Player
{

    public HumanPlayer()
    {
        super();
    }

    @Override
    public Coordinate makeMove()
    {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        Coordinate ans =  new Coordinate(scan.next());
        //scan.close();
        
        return ans;
    }
    
    

}
