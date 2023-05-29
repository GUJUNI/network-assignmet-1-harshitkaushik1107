/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

converts kilometers to meters and centimeters to meters using UDP
*/

import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);

                // Start a new thread to handle client requests
                ClientHandler clientHandler = new ClientHandler(serverSocket, receivePacket);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;

    public ClientHandler(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            String[] parts = clientMessage.split(" ");
            String conversionType = parts[0];
            double value = Double.parseDouble(parts[1]);

            double result;
            String response;

            if (conversionType.equalsIgnoreCase("km")) {
                result = value * 1000; // Convert kilometers to meters
                response = value + " km = " + result + " m";
            } else if (conversionType.equalsIgnoreCase("cm")) {
                result = value / 100; // Convert centimeters to meters
                response = value + " cm = " + result + " m";
            } else {
                response = "Invalid conversion type. Please specify 'km' or 'cm'.";
            }

            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
