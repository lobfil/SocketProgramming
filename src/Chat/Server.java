package Chat;

import java.io.IOException;
import java.net.*;

public class Server implements Runnable {
    
    private DatagramSocket socket;
    private DatagramPacket dataPacket;
    
    private final byte[] buffer = new byte[65507];
    private int port = 8000;
    private boolean flag = true;
    Peers peerList;
    Chat chat;

    
    public Server(Peers p, Chat c) {
    	
    	peerList = p;
    	chat = c;
    	
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    	while(flag)
    	{
    		dataPacket = new DatagramPacket(buffer, buffer.length);
    		try {
    			/*receiving an UDP package */
				socket.receive(dataPacket);
				
				String message = new String( dataPacket.getData());
            	InetAddress IPAddress = dataPacket.getAddress();
            	
            	String name = peerList.Peers.get(IPAddress); //translate ip into nickname
            	chat.messages.add(name +": "+ message);
            	
            	/*emergency kill*/
            	if(message=="STOP")
            	{
            		flag=false;
					System.out.println("Stopped by "+ name);
            	}
            	
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }              
    
    
}
