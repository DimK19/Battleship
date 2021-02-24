package backend;

public class CustomPair
{
    Coordinate c;
    int res;
    
    public CustomPair(Coordinate c, int res)
    {
        this.c = c;
        this.res = res;
    }
    
    public Coordinate getLeft()
    {
        return this.c;
    }
    
    public int getRight()
    {
        return this.res;
    }

}
