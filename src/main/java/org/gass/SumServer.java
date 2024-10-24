package org.gass;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SumServer {
    public static void main(String[] args) {
        int port = 1234;
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(port);
            System.out.println("Sum server is running...");
            byte[] data = new byte[30];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String receivedData = new String(packet.getData(), 0, packet.getLength());
            String[] numbers = receivedData.split(",");
            int num1 = Integer.parseInt(numbers[0]);
            int num2 = Integer.parseInt(numbers[1]);
            int sum = num1 + num2;

            byte[] response = ("Sum: " + sum).getBytes();
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

