/*Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Arithmetic operation using UDP 
*/

import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            int serverPort = 12345;

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            // Sending an arithmetic expression to the server
            String expression = "10 + 5";
            sendData = expression.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData());
            System.out.println("Server response: " + serverResponse);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Server started. Listening on port 12345...
Received from client: 10 + 5
Server response: 15.0
 */