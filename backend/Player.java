package backend;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Player
{
    private int movesPlayed, points;
    private ArrayList<Ship> ships;
    private Stack<Coordinate> allMovesStack;
    private boolean[][] hitArray;
    
    public Player()
    {
        this.movesPlayed = 0;
        this.points = 0;
        allMovesStack = new Stack<Coordinate>();
        
        hitArray = new boolean[20][20];
        /*
        for(int i = 0; i < 10; ++i)
            for(int j = 0; j < 10; ++j) hitArray[i][j] = false;
        */
        ships = new ArrayList<Ship>();
        ships.add(new Carrier());
        ships.add(new Battleship());
        ships.add(new Cruiser());
        ships.add(new Submarine());
        ships.add(new Destroyer());
    }
    
    public int getMovesPlayed()
    {
        return this.movesPlayed;
    }
    
    public int getActiveShips()
    {
        int counter = 0;
        for(Ship s : this.ships) if(!s.getState().equals("sunken")) counter++;
        return counter;
    }
    
    public int getTotalPoints()
    {
        return points;
    }
    
    public double getSuccessRate()
    {
        return 0;
    }
    
    public void incrementMoveCounter()
    {
        this.movesPlayed++;
    }
    
    public boolean hasLostAllShips()
    {
        for(Ship s : ships)
        {
            if(!s.getState().equals("sunken")) return false;
        }
        
        return true;
    }
    
    public void updateShips(int type)
    {
        if(type <= 0) return;
        else ships.get(--type).hit();
    }
    
    public Stack<Coordinate> getAllMovesStack()
    {
        return allMovesStack;
    }

    public void pushToMoves(Coordinate c)
    {
        this.allMovesStack.push(c);
        hitArray[c.getX()][c.getY()] = true;
    }
    
    public boolean hasBeenShot(Coordinate c)
    {
        return hitArray[c.getX()][c.getY()];
    }
    
    public abstract Coordinate makeMove();

}
