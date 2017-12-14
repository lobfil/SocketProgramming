import java.io.*;
import java.util.concurrent.*;


public class FileSender {
	
    long max =0; //number of packets that the file contains
    int index_sent = 0;	//how many packets are sent
    int index_conf = 0; //how many packets are confirmed
    Semaphore inFlight = new Semaphore(10);
    Semaphore mutex = new Semaphore(1, true);
    
    
    void confirm_n(int n)
    {
    	try {
			mutex.acquire();
	    	if(index_conf <= n)
	    	{
	    		index_sent=n+1;
	    	}
    		inFlight.release();
    		System.out.println("ACK-ed " + n);
	    	index_conf=n;
	    	mutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
	void start()
	{
		 try {
				Client c = new Client();
				File f= new File("file.txt");
				Producer p = new Producer(new FileInputStream(f));
	 	//class that produces 'packets'. the method read_n(int x) returns the x th packet of the file.
				
				Server s = new Server(this);
				Thread rcv = new Thread(s);
				rcv.start();
				
				
	        max = (f.length()/2042)+1; //number of packets that the file contains
	        System.out.println(f.length()/2042);
	        index_sent = 0;	//how many packets are sent
	        index_conf = 0; //how many packets are confirmed
	        
	        
	        while(index_sent<max && index_conf<max)
	        {
	        	inFlight.acquire();	
	        	mutex.acquire();//semaphore that doesn't allow more than 10 packets unconfirmed(aka inFlight)
	        	c.send(p.read_n(index_sent), index_sent);	//send the n-th paket
	        	System.out.println("sent " + index_sent);
	        	index_sent++;				//increment the no of packets inFlight
	        	mutex.release();
	        }
	        
	        } catch (IOException|InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
	}

}
