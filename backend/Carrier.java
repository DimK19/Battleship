package backend;

public class Carrier extends Ship
{    
    public Carrier()
    {
        super();
        this.numberOfSlots = 5;
        this.points = 350;
        this.sinkBonus = 1000;
        this.name = "Carrier";
    }
}
