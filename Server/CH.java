package Server;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import Model.*;

/*
Clase de conexión, se encarga de la comunicación entre el cliente y el servidor.
*/
public class CH implements Runnable {
    private Socket socket;
    private TCH hcc;
    private ObjectInputStream in;
    private ObjectOutputStream out;  
    private CCT controller;
    private long timeReceivedMessage;
    private volatile boolean runState = true;

    public CH(CCT controller,Socket socket) {
        this.controller = controller;
        TCH healthCareConnection = new TCH(this, 10000);
        new Thread(healthCareConnection).start();
        this.hcc = healthCareConnection;
        this.socket= socket;
        try {
            OutputStream os = socket.getOutputStream();
            this.out = new ObjectOutputStream(os);
            InputStream is = socket.getInputStream();
            this.in = new ObjectInputStream(is);
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e);
        }
      
    }



    public void sendBall(Ball ball) {
        try {
            Balldata ballData = new Balldata(ball);
            // System.out.println("enviando bola"+ ballData);
            out.writeObject(ballData);
            out.flush(); 
        } catch (Exception e) {
            System.out.println(
                    "hay un error :(" + "\n" + "Codigo de error: " + e);
        }
    }

    
    public void recieveBall() {
        Object object;
        // System.out.println("Esperando mensaje...");
        try {
            while (socket != null && socket.isConnected() && (object = in.readObject()) != null) {
                System.out.println("Recibido ping");
                if (object instanceof String && object.equals("ping")) {
                    System.out.println("Recibido ping");
                    recieveBall();
                    return;
                }
                // System.out.println("Recibido bolas");
                Balldata m = (Balldata) object;
                controller.recibirBall(m.transformData());
                long time = (System.currentTimeMillis());
                setTimeReceivedMessage(time);
            }
        } catch (Exception e) {
            System.err.println("Error en la conexion");
            killSocket();
        }
    }

    public void setTimeReceivedMessage(long timeReceivedMessage) {
        this.timeReceivedMessage = timeReceivedMessage;
    }


    public void setHCC(TCH hch) {
        this.hcc = hch;
    }


    public boolean ping() {
        try {
            // System.out.println("entra en ping");
            out.writeObject(new String("ping"));
            return true;
        } catch (Exception e) {
            System.out.println("Error en el envío del heartbeat: " + e);
            return false;
        }
    }

    public void run() {
        while (runState) {
            System.out.println("no se ha perdido la conexion");
            try {
                // Verificar si la conexión actual está cerrada
                if (socket == null || socket.isClosed() || !socket.isConnected()) {
                    System.out.println("Conexión perdida, intentando reconectar...");
                    Thread.sleep(1000);
                    // controller.reconnect(this);
                    runState = false;
                }

                // Leer mensajes entrantes
                if (socket != null && socket.isConnected()) {
                    recieveBall();
                }

                // Esperar un poco antes de verificar la conexión de nuevo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error en la reconexión: " + e);
            }
        }
    }

    public long getTimeReceivedMessage() {
        return timeReceivedMessage;
    }

    public synchronized void killSocket() {
    }

    public void stopHCC() {
        if (hcc != null) {
            hcc.pararEjecucion();
            hcc = null;
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void setObjectOutputStream(ObjectOutputStream out){
        this.out = out;
    }

    public void setObjectInputStream(ObjectInputStream in){
        this.in = in;
    }

    @Override
    public String toString() {
        return "esto existe";
    }

    

}