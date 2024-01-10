package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
Clase de conexión, se encarga de la comunicación entre el cliente y el servidor.
*/
public class CCT  {
    private Socket socket;
    private String address;
    private TCH hcc;
    private BufferedReader in;
    private PrintWriter out;
    private Integer PORT = 1234;
    private SC serverConnector;
    private CC clientConnector;

    private long timeReceivedMessage;
    private volatile boolean runState = true;
    private boolean wasClient = false;

    public CCT(String address) {
        this.address = address;
        try {
            this.address = address;
            boolean seHaPodidoConectarComoCliente;
            this.clientConnector = new CC(PORT, address);
            clientConnector.setIntentarReconectar(false);
            clientConnector.run();
            this.socket = clientConnector.getSocket();
            seHaPodidoConectarComoCliente = clientConnector.isConexionEstablecida();
           if(seHaPodidoConectarComoCliente){
               wasClient=true;
               System.out.println("Conexion establecida como cliente correctamente");
           }else {
               clientConnector.setIntentarReconectar(true);
               System.out.println("Abortando conexion como cliente...");
               // Iniciar el servidor
               this.serverConnector = new SC(PORT);
               serverConnector.run();
               this.socket = serverConnector.getSocket();
               System.out.println("Conexion establecida como servidor correctamente");
           }

            // Inicializar los objetos BufferedReader y PrintWriter
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void reconnect() {
        try {
            PORT = 1234;
            // Iniciar servidor y esperar conexiones entrantes
            System.out.println("Iniciando servidor...");
            serverConnector = new SC(PORT);
            serverConnector.run();
            socket = serverConnector.getSocket();
            TCH healthCareConnection = new TCH(this, 10000);
            setHCC(healthCareConnection);
            new Thread(healthCareConnection).start();

            // Reinicializar los objetos BufferedReader y PrintWriter
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conexión establecida correctamente");
        } catch (IOException ex) {
            System.err.println("No se ha podido conectar");
        } catch (NullPointerException e) {
            System.out.println("Error en la conexión: " + e);
        }
    }

    public void setHCC(TCH hch) {
        this.hcc = hch;
    }


    public void setTimeReceivedMessage(long timeReceivedMessage) {
        this.timeReceivedMessage = timeReceivedMessage;
    }

    public boolean ping() {
        try {
            out.println("ping");
            return true;
        } catch (Exception e) {
            System.out.println("Error en el envío del heartbeat: " + e);
            return false;
        }
    }

    public long getTimeReceivedMessage() {
        return timeReceivedMessage;
    }
    public synchronized void killSocket() {
        try {
            stopHCC();
            in.close();
            out.close();
            if(serverConnector!=null) {
                socket.close();
                if (!serverConnector.isSocketClosed()) {
                    serverConnector.killSocket();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket = null;
            PORT = null;
            System.err.println("Matando el socket...");
        }

    }
    public void stopHCC() {
        if (hcc != null) {
            hcc.pararEjecucion();
            hcc = null;
        }
    }

}

