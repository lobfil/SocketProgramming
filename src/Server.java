import java.net.*;

public class Server implements Runnable {
    
    private DatagramSocket socket;
    private DatagramSocket dataPacket;
    
    private final byte[] buffer = new byte[65507];
    private int port = 8000;
    private boolean flag = false;
    private Thread thread;
    
    public Server() {
        try {
            this.socket = new DatagramSocket(port, InetAddress.getByName(""));
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Nothing
    }
    
    private void startServer() { // Starts the server
        this.flag = true;
        
        thread = new Thread(this);
        thread.start();
    }
    
    private void resumeServer() { // Method to resume a server that was previously paused.
        this.flag = true;
    }
    
    public void stopServer() { // Stops the server.
        this.flag = false;
        
        if (this.thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
}
