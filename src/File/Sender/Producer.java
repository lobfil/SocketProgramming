import java.io.*;
import java.nio.ByteBuffer;


public class Producer {
	byte buffer[][] = new byte[20][2042];
	int putin = 0;
	int sent = 0;
	InputStream s;
	
	Producer(InputStream s)
	{
		this.s=s;
		for(int i=0;i<10;i++)
			fillOne();
			
	}
	
	public byte[] read_n(int n)
	{	
		if(n>sent)
		{
			sent++;
			fillOne();
		}
		return buffer[n%20];
	}
	
	public void fillOne()
	{
		try {
			byte[] a = new byte[2044];
			int length = s.read(a);
			
			byte[] b = ByteBuffer.allocate(4).putInt(length).array();
			
	    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    	outputStream.write(b);
	    	outputStream.write(a);
	    	
	    	buffer[putin] = outputStream.toByteArray();
			putin=(putin+1)%20;

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
