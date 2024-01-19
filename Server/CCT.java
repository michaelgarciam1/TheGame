package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Model.Ball;

/*
Clase de conexión, se encarga de la comunicación entre el cliente y el servidor.
*/
public class CCT {

    ArrayList<CH> channels;
    private SC serverConnector;
    private CC clientConnector;

    public CCT(String ip) {
        channels = new ArrayList<CH>();
        Socket sc = createConnection(ip);
        channels.add(new CH(this, sc));
    }

    private Socket createConnection(String ip) {
        int PORT = 1234;
        try {
            boolean seHaPodidoConectarComoCliente;

            this.clientConnector = new CC(PORT, ip);
            clientConnector.setIntentarReconectar(false);
            clientConnector.run();
            Socket socket = clientConnector.getSOCKET();
            seHaPodidoConectarComoCliente = clientConnector.isConexionEstablecida();
            if (seHaPodidoConectarComoCliente) {
                System.out.println("Conexion establecida como cliente correctamente");
            } else {
                clientConnector.setIntentarReconectar(true);
                System.out.println("Abortando conexion como cliente...");
                // Iniciar el servidor
                this.serverConnector = new SC(PORT);
                serverConnector.run();
                socket = serverConnector.getClsock();
                System.out.println("Conexion establecida como servidor correctamente");
            }
            System.out.println(socket);
            return socket;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    // public void reconnect(CH chanel) {
        
    //     try {
    //         int PORT = 1234;
    //         // Iniciar servidor y esperar conexiones entrantes
    //         System.out.println("Iniciando servidor...");
    //         serverConnector = new SC(PORT);
    //         serverConnector.run();
    //         Socket socket = serverConnector.getClsock();
    //         System.out.println("Conexion establecida como servidor correctamente");
          

    //         // Reinicializar los objetos BufferedReader y PrintWriter
    //         this.in = new ObjectInputStream(socket.getInputStream());
    //         this.out = new ObjectOutputStream(socket.getOutputStream());
    //     } catch (IOException ex) {
    //         System.err.println("No se ha podido conectar");
    //     } catch (NullPointerException e) {
    //         System.out.println("Error en la conexión: " + e);
    //     }
    // }

    public synchronized void killSocket(CH chanel) {
        try {
            chanel.stopHCC();
            if (serverConnector != null) {
                chanel.getSocket().close();
                if (!serverConnector.isSocketClosed()) {
                    serverConnector.killSocket();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chanel.setSocket(null);
            System.err.println("Matando el socket...");
            chanel.setObjectInputStream(null);
            chanel.setObjectOutputStream(null);
        }

    }

    public void enviarBall(Ball ball) {

    }

    public void recibirBall(Ball ball) {

    }
}