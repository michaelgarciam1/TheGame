package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* Conector para actuar como servidor*/

public class SC implements Runnable{
    private int PORT;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public SC(int port) {
        this.PORT = port;
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Conectando como servidor...");
            this.clientSocket = serverSocket.accept();
        } catch (Exception e) {
            System.out.println("ServerConnector error: " + e);
        }
    }

    public void killSocket(){
        try{
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isSocketClosed(){
        return serverSocket.isClosed();
    }
}