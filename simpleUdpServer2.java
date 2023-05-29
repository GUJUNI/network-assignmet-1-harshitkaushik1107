/*Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

String is palidrome or not using UDP
*/

import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData());
                System.out.println("Received from client: " + clientMessage);

                // Remove leading and trailing white spaces
                String cleanedMessage = clientMessage.trim();

                // Reverse the message
                StringBuilder reversedMessage = new StringBuilder(cleanedMessage);
                reversedMessage.reverse();

                // Check if the message is a palindrome
                boolean isPalindrome = cleanedMessage.equals(reversedMessage.toString());

                String serverResponse;
                if (isPalindrome) {
                    serverResponse = "The message is a palindrome.";
                } else {
                    serverResponse = "The message is not a palindrome.";
                }

                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = serverResponse.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
