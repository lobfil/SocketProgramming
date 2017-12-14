package File.Receiver;

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
    
    
    public void send(int index) throws IOException{
    	
    	byte[] a = ByteBuffer.allocate(4).putInt(index).array(); //get 4 bytes for index no.
    	
        DatagramPacket packet = new DatagramPacket(a, a.length, IPAddress, 8001);   //send the packet
        socket.send(packet);
    	}

}
