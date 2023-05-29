/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Reverse string using TCP
*/

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            String userInput;

            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Server: " + serverMessage);

                if (serverMessage.equals("exit")) {
                    break;
                }

                // Get user input from the console
                userInput = stdIn.readLine();

                // Send user input to the server
                out.println(userInput);

                // Receive and display the reversed string from the server
                String reversedString = in.readLine();
                System.out.println("Reversed String: " + reversedString);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
 Output
 Server started. Listening on port 12345...
Server: Enter a string to reverse (or type 'exit' to quit):
Hello World
Server: dlroW olleH
OpenAI ChatGPT
Server: TPahC IAepnO
Reversed String: TPahC IAepnO
Server: Enter a string to reverse

 */