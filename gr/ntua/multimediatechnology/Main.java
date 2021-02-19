package gr.ntua.multimediatechnology;

import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException, OverlapTilesException, OversizeException, AdjacentTilesException, InvalidCountException
    {
        Game g = new Game();
        g.play();
    }

}
