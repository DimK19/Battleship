package backend;

import java.util.ArrayList;

public class Board
{
    static String[] entities = {"Water", "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
    private Cell[][] grid = new Cell[10][10];
    
    public Board(ArrayList<String> positions) throws OverlapTilesException, OversizeException, AdjacentTilesException, InvalidCountException
    {
        for(int i = 0; i < 10; ++i)
            for(int j = 0; j < 10; ++j) grid[i][j] = new Cell();
        boolean[] found = new boolean[5]; // default value is false
        for(String p: positions)
        {
            // convert to integers
            String[] arr = p.split(",");
            int[] numbers = new int[arr.length];
            for(int i = 0; i < arr.length; i++) numbers[i] = Integer.parseInt(arr[i]);
            
            // check validity
            if(numbers[0] < 1 || numbers[0] > 5) throw new OversizeException();
            if(found[numbers[0] - 1]) throw new InvalidCountException("No more than 1 of each ship type allowed");
            if(numbers[1] < 0 || numbers[1] > 9 || numbers[2] < 0 || numbers[2] > 9) throw new OversizeException();
            placeShip(numbers[0], numbers[1], numbers[2], numbers[3]);
            found[numbers[0] - 1] = true;
        }
    }
    
    public void placeShip(int type, int y, int x, int orientation) throws OverlapTilesException, OversizeException, AdjacentTilesException
    {
        int length = 0;
        switch(type)
        {
            case 1:
                length = new Carrier().getSlots();
                break;
            case 2:
                length = new Battleship().getSlots();
                break;
            case 3:
                length = new Cruiser().getSlots();
                break;
            case 4:
                length = new Submarine().getSlots();
                break;
            case 5:
                length = new Destroyer().getSlots();
                break;
        }
        
        // 1 means horizontal, 2 vertical
        if(orientation == 1)
        {
            if(x + length - 1 > 10) throw new OversizeException();
            for(int i = x; i < x + length; ++i)
            {
                if(grid[i][y].getOccupation() != 0) throw new OverlapTilesException();
                else if(y - 1 >= 0 && grid[i][y - 1].getOccupation() != 0) throw new AdjacentTilesException();
                else if(y + 1 < 10 && grid[i][y + 1].getOccupation() != 0) throw new AdjacentTilesException();
                else grid[i][y].setOccupation(type);
            }
        }
        else if(orientation == 2)
        {
            if(y + length - 1 > 10) throw new OversizeException();
            for(int j = y; j < y + length; ++j)
            {
                if(grid[x][j].getOccupation() != 0) throw new OverlapTilesException();
                else if(x - 1 >= 0 && grid[x - 1][j].getOccupation() != 0) throw new AdjacentTilesException();
                else if(x + 1 < 10 && grid[x + 1][j].getOccupation() != 0) throw new AdjacentTilesException();
                else grid[x][j].setOccupation(type);
            }
        }
    }
    
    public int getHitOutcome(Coordinate c)
    {
        // normally returns what the cell is occupied by (water or ship id)
        // -1 in cases of error (invalid coordinate or one that has been shot already)
        if(!Coordinate.isValidCoordinate(c)) return -1;
        int x = c.getX(), y = c.getY();
        if(grid[x][y].hasBeenShot()) return -1;
        grid[x][y].shoot();
        
        return grid[x][y].getOccupation();
    }
    
    // for testing
    public String toString()
    {
        String res = "";
        for(int i = 0; i < 10; ++i)
        {
            for(int j = 0; j < 10; ++j)
                res += grid[j][i].toString();
            res += "\n";
        }
        
        return res;
    }
    
    private class Cell
    {
        private boolean shot;
        protected int occupation = 0;
        /* 0: empty
         * 1: Carrier
         * 2: Battleship
         * 3: Cruiser
         * 4: Submarine
         * 5: Destroyer
         */
        public Cell()
        {
            this.shot = false;
        }
        
        public void setOccupation(int o)
        {
            this.occupation = o;
        }
        
        public int getOccupation()
        {
            return occupation;
        }
        
        public boolean hasBeenShot()
        {
            return shot;
        }
        
        public void shoot()
        {
            this.shot = true;
        }
        
        // for testing
        public String toString()
        {
            return String.valueOf(this.occupation);
        }
    }
}
