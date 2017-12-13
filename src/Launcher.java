import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Launcher {
    
    public static void main(String[] args) {
        Peers peer=new Peers();
        Chat chat = new Chat();
        try {
        	/* here we add the peers */
			peer.Peers.put(InetAddress.getByName("127.0.0.1"), "Rick");
			peer.Peers.put(InetAddress.getByName("127.0.0.2"), "Morty");
			peer.Peers.put(InetAddress.getByName("127.0.0.3"), "Jerry");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Server server = new Server(peer, chat);
        Client c = new Client(peer, chat);
   
        Thread s = new Thread(server);
        s.start();

        String in = "";
        String[] tokens;
              
       while(true)
       {
    	   String command=""; //command can be PRINT or SEND
    	   String argument=""; // the message to send

           try {
               in = reader.readLine();
           } catch (IOException e) {
               e.printStackTrace();
           }

           tokens = in.split(" ", 2);
           command = tokens[0];
           argument = tokens[1];
    	   
    	   if(command == "SEND")
    	   {
    		   c.send(argument);
    	   }
    	   if(command == "PRINT")
    	   {
    		   for(String m:chat.messages)
    		   {
    			   System.out.println(m);
    		   }
    	   }
       }
        
    }
}
