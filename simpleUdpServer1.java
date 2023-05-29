/*Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1
*/
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData());
                System.out.println("Received from client: " + clientMessage);

                // Parse the client message for arithmetic operation
                String[] parts = clientMessage.trim().split(" ");
                double operand1 = Double.parseDouble(parts[0]);
                double operand2 = Double.parseDouble(parts[2]);
                String operator = parts[1];

                double result = 0;
                if (operator.equals("+")) {
                    result = operand1 + operand2;
                } else if (operator.equals("-")) {
                    result = operand1 - operand2;
                } else if (operator.equals("*")) {
                    result = operand1 * operand2;
                } else if (operator.equals("/")) {
                    result = operand1 / operand2;
                }

                String serverResponse = String.valueOf(result);

                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendData = serverResponse.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
