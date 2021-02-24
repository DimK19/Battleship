package backend;

public class Ship
{
    int numberOfSlots, points, sinkBonus;
    int hitsTaken;
    String name;
    
    public Ship()
    {
        this.hitsTaken = 0;
        this.points = 0;
        this.sinkBonus = 0;
        this.name = "Generic Ship";
    }
    
    public String getState()
    {
        // untouched - hit - sunken
        String s = "";
        if(this.hitsTaken == 0) s = "untouched";
        else if(this.hitsTaken < this.numberOfSlots) s = "hit";
        else s = "sunken";
        
        return s;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getSlots()
    {
        return this.numberOfSlots;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public int getSinkBonus()
    {
        return sinkBonus;
    }
    
    public void hit()
    {
        hitsTaken++;
    }
}
