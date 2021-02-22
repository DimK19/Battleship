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
        this.x = userInput.toUpperCase().charAt(0) - (int)'A';
        this.y = userInput.charAt(1) - (int)'1';
        if(userInput.length() == 3) this.y = 9;
    }
    
    public static boolean isValidCoordinate(Coordinate c)
    {
        return c.getX() >= 0 && c.getX() < 10 && c.getY() >= 0 && c.getY() < 10;
    }
    
    public static boolean isValidCoordinate(String s)
    {
        if(s.length() != 2 && s.length() != 3) return false;
        int x = s.toUpperCase().charAt(0) - (int)'A';
        int y = s.charAt(1) - (int)'1';
        System.out.println(x);
        System.out.println(y);
        if(x < 0 || x > 9) return false;
        if(s.length() == 2)
        {
            if(y < 0 || y > 9) return false;
        }
        else
        {
            int y2 = s.charAt(2) - (int)'0';
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
}
