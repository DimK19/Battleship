package gr.ntua.multimediatechnology;

import java.util.ArrayList;

public abstract class Player
{
    private int movesPlayed;
    private ArrayList<Ship> ships;
    
    public Player()
    {
        this.movesPlayed = 0;
        
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
        if(type == 0) return;
        else ships.get(--type).hit();
    }
    
    public abstract Coordinate makeMove();

}
