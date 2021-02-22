package backend;

@SuppressWarnings("serial")
public class InvalidCountException extends Exception
{
    public InvalidCountException()
    {
        super("Invalid Count Exception");
    }
    
    public InvalidCountException(String message)
    {
        super(message);
    }
}
