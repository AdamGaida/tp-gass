package org.gass;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPSocketServer {
    public static void main(String[] args) {
        int port = 1234;
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(port);
            System.out.println("Welcome to UDP socket server...");
            byte[] data = new byte[30];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String receivedData = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received from client (" + packet.getAddress() + ":" + packet.getPort() + "):");
            System.out.println(receivedData);

            byte[] response = ("Well received!").getBytes();
            packet.setData(response);
            packet.setLength(response.length);
            socket.send(packet);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
