package org.gass;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSocketClient {
    public static void main(String[] args) {
        int port = 1234;
        String host = "localhost";
        InetAddress address;
        DatagramPacket packet;
        DatagramSocket socket = null;

        try {
            address = InetAddress.getByName(host);
            byte[] data = ("Hello from " + address.getHostName() + ":" + port).getBytes();
            packet = new DatagramPacket(data, data.length, address, port);
            socket = new DatagramSocket();
            socket.send(packet);

            byte[] response = new byte[30];
            packet.setData(response);
            packet.setLength(response.length);
            socket.receive(packet);
            String receivedData = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received from server (" + packet.getAddress() + ":" + packet.getPort() + "):");
            System.out.println(receivedData);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

