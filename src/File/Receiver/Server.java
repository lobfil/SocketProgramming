import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Server implements Runnable {
	
	private DatagramSocket socket;
    private DatagramPacket dataPacket;
    
    private final byte[] buffer = new byte[4096];
    private int port = 8000;
    private boolean flag = true;
    
    FileRcv fileRcv;
    
    Server(FileRcv fs)
    {
    	fileRcv=fs;
    	
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
				 byte[] a = dataPacket.getData();
				 
				 byte[] n = {a[0],a[1],a[2],a[3]}; 
				 byte[] pLength = {a[4],a[5],a[6],a[7]};
				int packetId = ByteBuffer.wrap(n).getInt();
				
				System.out.println("Received: " + packetId);
				
				if(packetId==fileRcv.index+1)
				{
					fileRcv.indexM.acquire();
					fileRcv.index++;
					fileRcv.indexM.release();
				
					
					int length = ByteBuffer.wrap(pLength).getInt();

					for(int i=0; i<a.length-8;i++)
						a[i]=a[i+8];
				 
					a=Arrays.copyOf(a, length);
					fileRcv.appendBytes(a);
				}
				
				
				fileRcv.confirm_n();
				
				
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }              
}

