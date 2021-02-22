package backend;

import java.util.Scanner;

public class HumanPlayer extends Player
{
    private Coordinate latestMove;

    public HumanPlayer()
    {
        super();
        latestMove = new Coordinate(-1, -1);
    }

    // for command line implementation
    @Override
    public Coordinate makeMove()
    {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        Coordinate ans =  new Coordinate(scan.next());
        //scan.close();
        
        return ans;
    }
    
    // for GUI
    public Coordinate getLatestMove()
    {
        return latestMove;
    }

    public void setLatestMove(Coordinate latestMove)
    {
        this.latestMove = latestMove;
    }

}
