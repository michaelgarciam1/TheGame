package Server;

import java.net.Socket;

public class CC implements Runnable {
    private Socket socket;
    private int port;
    private String address;

    private boolean conexionEstablecida = false;

    private volatile boolean intentarReconectar = true;
    public CC(int port, String address) {
        this.port = port;
        this.address = address;
    }
    @Override
    public void run() {
            try {
                System.out.println("Conectando como cliente...");
                while (this.socket == null) {
                    this.socket = new Socket(this.address, this.port);
                }
                conexionEstablecida = true;
            } catch (Exception e) {
                System.out.println("Esperando conexión...");
                try {
                    Thread.sleep(5000);
                    if (intentarReconectar) {
                        run();
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

    }

       public Socket getSocket() {
        return socket;
    }

    public boolean isConexionEstablecida() {
        return conexionEstablecida;
    }

    public void setIntentarReconectar(boolean intentarReconectar) {
        this.intentarReconectar = intentarReconectar;
    }
}

    

