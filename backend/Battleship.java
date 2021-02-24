package backend;

public class Battleship extends Ship
{

    public Battleship()
    {
        super();
        this.numberOfSlots = 4;
        this.points = 250;
        this.sinkBonus = 500;
        this.name = "Battleship";
    }

}
