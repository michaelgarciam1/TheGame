package Server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.ArrayList;

import Model.Ball;
import Controller.TGPCT;
import Data.*;
import Data.PeerLocation;
import Interlocutor.Peer;

/*
Clase de conexión, se encarga de la comunicación entre el cliente y el servidor.
*/
public class CCT {
    ArrayList<CH> channels;
    private TGPCT controller;

    public CCT(String ip, TGPCT controller, ArrayList<Peer> configurations) {
        this.controller = controller;
        channels = new ArrayList<CH>();
        loadConfiguration(configurations);
    }

    private void loadConfiguration(ArrayList<Peer> configurations) {
        for (Peer peer : configurations) {
            Thread threadHilo = new Thread(() -> {
                Socket sc = createConnection(peer.getIp(), peer.getPort());
                CH chanel = new CH(this, sc, peer);
                channels.add(chanel);
                new Thread(chanel).start();
            });
            threadHilo.start();
        }
    }

    public boolean canSend(Ball ball, PeerLocation location) {
        for (CH channel : channels) {
            if (channel.getPeer().getLocation() == location) {
                return true;
            }
        }
        return false;

    }

    public Socket createConnection(String ip, int port) {
        Socket sc = null;
        while (sc == null) {
            sc = createCon(ip, port);
        }
        return sc;
    }

    private Socket createCon(String ip, int port) {
        SC serverConnector;
        CC clientConnector;
        try {
            boolean seHaPodidoConectarComoCliente;
            clientConnector = new CC(port, ip);
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
                serverConnector = new SC(port);
                serverConnector.run();
                socket = serverConnector.getClientSocket();
                System.out.println("Conexion establecida como servidor correctamente");
            }
            System.out.println(socket);
            return socket;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public void reconnect(CH chanel) {
        Socket socket = createConnection(chanel.getIp(), chanel.getPort());
        chanel.initChanel(socket);

    }

    public void enviarBall(Ball ball, PeerLocation location) {
        for (CH channel : channels) {
            if (channel.getPeer().getLocation() == location) {
                channel.sendBall(ball);
            }
        }
    }

    public void recibirBall(Ball ball) {
        // System.out.println("Recibiendo bola");
        // System.out.println(ball);
        controller.ballRecieved(ball);
    }
}