package File.Sender;

import java.io.*;
import java.net.*;
import java.nio.*;

public class Client {
    
    private DatagramSocket socket;
    InetAddress IPAddress;
    
    
    Client() throws SocketException, UnknownHostException
    {
        socket = new DatagramSocket();     
        IPAddress = InetAddress.getByName("127.0.0.1");
    }
    
    
    public void send(byte[] b, int index) throws IOException{
    	
    	byte[] a = ByteBuffer.allocate(4).putInt(index).array(); //get 4 bytes for index no.
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	outputStream.write(a);
    	outputStream.write(b);
    	
    	byte c[] = outputStream.toByteArray(); // index (4bytes) + data(2044byes)
    	
    	
        DatagramPacket packet = new DatagramPacket(c, c.length, IPAddress, 8000);   //send the packet
        socket.send(packet);
    	}

}
