import java.net.InetAddress;
import java.net.UnknownHostException;

public class Peer {
    
    private String name;
    private InetAddress ipAddress;
    private Client client;
    private Server server;
    
    public Peer(String name, InetAddress ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }
    
    public void createServerClient() { // Starts peer server and client.
        this.client = new Client();
        this.server = new Server();
    }

    public void stopServerClient() { // Stops server and client.
        if ((this.server != null) || (this.client != null)) {
            this.server.stopServer();
        }
    }
}
