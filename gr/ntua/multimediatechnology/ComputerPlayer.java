package gr.ntua.multimediatechnology;

import java.util.Random;

public class ComputerPlayer extends Player
{

    public ComputerPlayer()
    {
        super();
    }

    @Override
    public Coordinate makeMove()
    {
        // TODO Auto-generated method stub
        Random rn = new Random();
        return new Coordinate(rn.nextInt(10), rn.nextInt(10));
    }

}
