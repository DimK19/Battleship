package gr.ntua.multimediatechnology;

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
        this.y = userInput.charAt(1) - (int)'0';
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
