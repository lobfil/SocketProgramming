import java.io.IOException;
import java.net.SocketTimeoutException;

public class Server
{
    public static void main(String [] args) throws IOException
    {
        int port = Integer.parseInt(args[0]);

	/* Code should be placed here*/

        try 	//try-catch is used for handling exceptions in underlying methods
        {

	/* and here*/

        } catch(SocketTimeoutException s)
        {
            System.out.println("Socket timed out!");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
