import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.*;


public class FileRcv {
	
    Semaphore mutex = new Semaphore(1, true);
    Semaphore indexM = new Semaphore(1, true);
    FileOutputStream f;
	Client c;
	Server s;
	public int index=-1;
	
	void confirm_n()
	{
		try {
			indexM.acquire();
			c.send(index);
			indexM.release();
			System.out.println("Ack-ing: " + index);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	void start()
	{
		try {
			f = new FileOutputStream(new File("file.txt"),true);
			c = new Client();
			s = new Server(this);
			Thread t = new Thread(s);
			t.start();
			
		} catch (FileNotFoundException | SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	void appendBytes(byte[] b){

		try {
			mutex.acquire();
			f.write(b);
			System.out.println("assembled " + b.length);
			f.flush();
			mutex.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
