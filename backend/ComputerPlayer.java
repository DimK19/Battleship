package backend;

import java.util.Random;
import java.util.Stack;

public class ComputerPlayer extends Player
{
    Random rn;
    Stack<Coordinate> candidateMoves;
    
    public ComputerPlayer()
    {
        super();
        rn = new Random();
        candidateMoves = new Stack<Coordinate>();
    }

    @Override
    public Coordinate makeMove()
    {
        // first move of the game
        if(allMovesStack.isEmpty()) return randomMove();
        
        CustomPair previousMove = allMovesStack.peek();
        
        if(previousMove.getRight() == 0)
        {
            if(candidateMoves.isEmpty()) return randomMove();
            else return candidateMoves.pop();
        }
        else
        {
            candidateMoves.clear();
            Coordinate previousCoordinate = previousMove.getLeft();
            int x = previousCoordinate.getX();
            int y = previousCoordinate.getY();
            if(Coordinate.isValidCoordinate(x + 1, y) && !hasBeenShot(x + 1, y))
                candidateMoves.add(new Coordinate(x + 1, y));
            if(Coordinate.isValidCoordinate(x - 1, y) && !hasBeenShot(x - 1, y))
                candidateMoves.add(new Coordinate(x - 1, y));
            if(Coordinate.isValidCoordinate(x, y + 1) && !hasBeenShot(x, y + 1))
                candidateMoves.add(new Coordinate(x, y + 1));
            if(Coordinate.isValidCoordinate(x, y - 1) && !hasBeenShot(x, y - 1))
                candidateMoves.add(new Coordinate(x, y - 1));
            
            return candidateMoves.isEmpty() ? randomMove() : candidateMoves.pop();
        }
    }
    
    private Coordinate randomMove()
    {
        Coordinate randomMove;
        
        do randomMove = new Coordinate(rn.nextInt(10), rn.nextInt(10));
        while(hasBeenShot(randomMove));
        
        return randomMove;
    }
}
