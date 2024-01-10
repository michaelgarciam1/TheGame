package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* Conector para actuar como servidor*/

public class SC implements Runnable{
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;

    public SC(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Conectando como servidor...");
            this.socket = serverSocket.accept();
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

