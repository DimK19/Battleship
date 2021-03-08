package backend;

public class Coordinate
{
    private int x, y;

    public Coordinate()
    {
        this.x = 0;
        this.y = 0;
    }
    
    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }  
    
    public Coordinate(String userInput)
    {
        this.x = Utilities.letterToInt(userInput.toUpperCase().charAt(0));
        this.y = Utilities.numCharToInt(userInput.charAt(1));
        if(userInput.length() == 3) this.y = 9; // last row (10)
    }
    
    public static boolean isValidCoordinate(int x, int y)
    {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
    
    public static boolean isValidCoordinate(Coordinate c)
    {
        return c.getX() >= 0 && c.getX() < 10 && c.getY() >= 0 && c.getY() < 10;
    }
    
    public static boolean isValidCoordinate(String s)
    {
        if(s.length() != 2 && s.length() != 3) return false;
        int x = Utilities.letterToInt(s.toUpperCase().charAt(0));
        int y = Utilities.numCharToInt(s.charAt(1));
        
        if(x < 0 || x > 9) return false;
        if(s.length() == 2)
        {
            if(y < 0 || y > 9) return false;
        }
        else
        {
            int y2 = Utilities.numCharToInt(s.charAt(2)) + 1; // + 1 because 0 would be mapped to -1
            if(y != 0 || y2 != 0) return false;
        }
        
        return true;
    }
    
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    
    public String toString()
    {
        return String.valueOf((char)(x + 'A')) + String.valueOf(y + 1);
    }
}
