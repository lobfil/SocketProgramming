import java.io.IOException;
import java.net.*;
import java.util.Iterator;
import java.util.Map;

public class Client {
    
    private DatagramSocket socket;
    private byte[] buffer = new byte[65507];
    Peers peerList;
    Chat chat;
    
    Client(Peers p, Chat c)
    {
    	peerList = p;
    	chat = c;
    }
    
    public void send(String s){
    	
    	/*broadcasts a message to all the peers*/
    	
    	for (InetAddress ip : peerList.Peers.keySet()) {
    		try {
        		buffer = s.getBytes();
				socket = new DatagramSocket();
				DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, ip, 8000);
				socket.send(sendPacket);			
			} catch (IOException e) {
				e.printStackTrace();
			}
  
    	}
		chat.messages.add(s);
		//copy message to self

    }

}
