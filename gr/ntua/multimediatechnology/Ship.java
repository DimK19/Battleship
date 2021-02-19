package gr.ntua.multimediatechnology;

public class Ship
{
    protected int numberOfSlots, points, sinkBonus;
    public int hitsTaken;
    
    public Ship()
    {
        this.hitsTaken = 0;
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
    
    public int getSlots()
    {
        return this.numberOfSlots;
    }
    
    public void hit()
    {
        hitsTaken++;
    }
}
