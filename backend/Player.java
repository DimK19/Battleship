package backend;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Player
{
    private int movesPlayed, points;
    private ArrayList<Ship> ships;
    private Stack<CustomPair> allMovesStack;
    private boolean[][] hitArray;
    
    public Player()
    {
        this.movesPlayed = 0;
        this.points = 0;
        allMovesStack = new Stack<CustomPair>();
        
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
    
    public Stack<CustomPair> getAllMovesStack()
    {
        return allMovesStack;
    }

    public void pushToMoves(Coordinate c, int outcome)
    {
        this.allMovesStack.push(new CustomPair(c, outcome));
        hitArray[c.getX()][c.getY()] = true;
    }
    
    public boolean hasBeenShot(Coordinate c)
    {
        return hitArray[c.getX()][c.getY()];
    }
    
    public String getShipReport()
    {
        String r = "";
        
        for(Ship s : this.ships)
        {
            String temp = s.getName() + ' ' + s.getState() + '\n';
            r += temp;
        }
        
        return r;
    }
    
    public String getShotReport()
    {
        String r = "";
        
        for(int i = allMovesStack.size() - 1; i >= Math.max(allMovesStack.size() - 5, 0); --i)
        {
            CustomPair cm = allMovesStack.get(i);
            System.out.println(cm.getLeft().toString() + ' ' + Board.entities[cm.getRight()]);
            r += cm.getLeft().toString() + ' ' + Board.entities[cm.getRight()] + '\n';
        }

        return r;
    }
    
    public abstract Coordinate makeMove();

}
