package backend;

import java.util.Random;

public class ComputerPlayer extends Player
{
    Random rn;
    
    
    public ComputerPlayer()
    {
        super();
        rn = new Random();
    }

    @Override
    public Coordinate makeMove()
    {
        Coordinate candidateMove;
        
        do candidateMove = new Coordinate(rn.nextInt(10), rn.nextInt(10));
        while(hasBeenShot(candidateMove));
        
        pushToMoves(candidateMove);
        return candidateMove;
    }

}
