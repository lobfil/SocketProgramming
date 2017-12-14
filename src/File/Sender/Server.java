import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class Server implements Runnable {
	
	private DatagramSocket socket;
    private DatagramPacket dataPacket;
    
    private final byte[] buffer = new byte[65507];
    private int port = 8001;
    private boolean flag = true;
    
    FileSender fileSender;
    
    Server(FileSender fs)
    {
    	fileSender=fs;
    	
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
				socket.receive(dataPacket);
				int confirmation = ByteBuffer.wrap(dataPacket.getData()).getInt();
				fileSender.confirm_n(confirmation);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }              
}

