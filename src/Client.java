import java.io.IOException;
import java.net.*;

public class Client implements Runnable {
    
    private DatagramSocket socket;
    private final byte[] buffer = new byte[65507];
    
    private int port = 8000;
    private boolean flag = false;
    private Thread thread;
    
    public Client() {
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        // Nothing
    }
    
    public void startClient() {
        this.flag = true;
        
        thread = new Thread(this);
        thread.start();
    }
}
