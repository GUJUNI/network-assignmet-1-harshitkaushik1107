Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Q4: This program is a multi-threaded UDP client-server application in Java that demonstrates 
how to send and receive messages between a client and server over UDP protocol, 
allowing for low-latency communication without establishing a connection.
The server listens for incoming packets on a specified port and responds to each client 
request by processing the message, converting it to uppercase, and sending it back to the client.

import java.io.*;
import java.net.*;

class UDPServer {
   public static void main(String args[]) throws Exception {
      DatagramSocket serverSocket = new DatagramSocket(9876);

      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];

      while (true) {
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

         serverSocket.receive(receivePacket);

         String sentence = new String(receivePacket.getData());

         InetAddress IPAddress = receivePacket.getAddress();

         int port = receivePacket.getPort();

         System.out.println("Received data from client: " + sentence.trim());

         // Create a new thread to handle the client request
         Thread clientThread = new Thread(new ClientHandler(serverSocket, IPAddress, port, sentence.trim()));
         clientThread.start();
      }
   }
}

class ClientHandler implements Runnable {
   private DatagramSocket serverSocket;
   private InetAddress IPAddress;
   private int port;
   private String sentence;

   public ClientHandler(DatagramSocket serverSocket, InetAddress IPAddress, int port, String sentence) {
      this.serverSocket = serverSocket;
      this.IPAddress = IPAddress;
      this.port = port;
      this.sentence = sentence;
   }

   public void run() {
      try {
         // Process the client request
         String capitalizedSentence = sentence.toUpperCase();

         byte[] sendData = capitalizedSentence.getBytes();

         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

         serverSocket.send(sendPacket);

         System.out.println("Sent data to client: " + capitalizedSentence);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
