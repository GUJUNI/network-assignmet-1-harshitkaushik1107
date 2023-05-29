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

class UDPClient {
   public static void main(String args[]) throws Exception {
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

      DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = InetAddress.getByName("localhost");

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];

      System.out.println("Enter a message: ");
      String sentence = inFromUser.readLine();

      sendData = sentence.getBytes();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

      clientSocket.send(sendPacket);

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      clientSocket.receive(receivePacket);

      String modifiedSentence = new String(receivePacket.getData());

      System.out.println("Received data from server: " + modifiedSentence.trim());

      clientSocket.close();
   }
}


output:-
	
Server Console:
Received data from client: Hello from client 1
Received data from client: Hello from client 2
Received data from client: Hello from client 3

Client Console:

Enter a message: Hello from client 1
Received data from server: HELLO FROM CLIENT 1

Enter a message: Hello from client 2
Received data from server: HELLO FROM CLIENT 2

Enter a message: Hello from client 3
Received data from server: HELLO FROM CLIENT 3
