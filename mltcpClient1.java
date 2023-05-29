/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Q4 Multitreading tcp
*/
import java.io.*;
import java.net.Socket;

public class MultiThreadedTCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server. Enter messages:");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Thread serverThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = serverReader.readLine()) != null) {
                        System.out.println("Server response: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();

            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                writer.println(clientMessage);
            }

            reader.close();
            writer.close();
            serverReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Connected to server. Enter messages:
Hello
Server response: HELLO
World
Server response: WORLD

 */