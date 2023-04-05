
package modelo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSocket {

    private static final int BUFFER_SIZE = 1024;

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public UDPSocket(String serverHost, int serverPort) throws Exception {
        this.serverAddress = InetAddress.getByName(serverHost);
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    public void connect() throws Exception {
        // No es necesario establecer la conexión en UDP, por lo que este método no hace nada
    }

    public void send(String message) throws Exception {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
        socket.send(sendPacket);
    }

    public String receive() throws Exception {
        byte[] receiveBuffer = new byte[BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        return response;
    }

    public void close() {
        socket.close();
    }
}