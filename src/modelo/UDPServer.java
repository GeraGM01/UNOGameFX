package modelo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    // Establece el puerto de escucha del servidor
    private static final int PORT = 9000;
    // Establece el tamaño del buffer utilizado para recibir y enviar mensajes
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        SentenciasSQL model = new SentenciasSQL();
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            // Muestra un mensaje en la consola para indicar que el servidor se ha iniciado
            System.out.println("Server started on port " + PORT);

            while (true) {
                // Crea un buffer para recibir mensajes entrantes
                byte[] receiveBuffer = new byte[BUFFER_SIZE];
                // Crea un paquete de datagrama para recibir el mensaje en el buffer
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                // Espera a recibir un mensaje entrante
                serverSocket.receive(receivePacket);

                // Obtiene la dirección IP del cliente que envió el mensaje
                InetAddress clientAddress = receivePacket.getAddress();
                // Obtiene el puerto del cliente que envió el mensaje
                int clientPort = receivePacket.getPort();
                // Obtiene el mensaje del paquete recibido y lo convierte a una cadena
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Muestra un mensaje en la consola que indica que se ha recibido una solicitud
                System.out.println("Received request from " + clientAddress + ":" + clientPort + ": " + request);
                String[] parts = request.split(":");
                
                //Aqui validamos en la BD
                String bandera = model.consultaDB(parts[0], parts[1]);
                System.out.println(bandera);
                // Convierte el mensaje recibido a mayúsculas
                String response = bandera;
                // Convierte la respuesta en un buffer de bytes
                byte[] sendBuffer = response.getBytes();
                // Crea un paquete de datagrama para enviar la respuesta al cliente
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                // Envía la respuesta al cliente
                serverSocket.send(sendPacket);
            }
        } catch (IOException ex) {
            // Muestra cualquier excepción que se produzca en la consola
            ex.printStackTrace();
        }
    }
}
