/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

converts kilometers to meters and centimeters to meters using UDP 
*/

import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter conversion type (km/cm):");
                String conversionType = scanner.nextLine();

                System.out.println("Enter value:");
                double value = scanner.nextDouble();

                String message = conversionType + " " + value;
                byte[] sendData = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                clientSocket.receive(receivePacket);

                String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server response: " + serverResponse);

                if (serverResponse.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 Output
 Server started. Listening on port 12345...

 Enter conversion type (km/cm):
km
Enter value:
5.5
Server response: 5.5 km = 5500.0 m

Enter conversion type (km/cm):
cm
Enter value:
800
Server response: 800.0 cm = 8.0 m

Enter conversion type (km/cm):
km
Enter value:
exit
Server response: exit

 */