package Server;

import java.net.Socket;

public class PID implements Runnable{
    private Socket socket;

    public PID(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            while (socket!=null && !socket.isClosed()) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Error en el PID: " + e);
        }
    }

    public String getIP() {
        return socket.getInetAddress().getHostAddress();
    }

   
    
}
