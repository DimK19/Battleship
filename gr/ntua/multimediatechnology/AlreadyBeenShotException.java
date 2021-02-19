package gr.ntua.multimediatechnology;

@SuppressWarnings("serial")
public class AlreadyBeenShotException extends Exception
{

    public AlreadyBeenShotException()
    {
        super("Selected Tile Has Been Shot Already");
    }

}
