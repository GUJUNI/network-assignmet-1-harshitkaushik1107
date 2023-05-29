/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Menu driven program to store employee details (like add,delete, modify, display info) using TCP
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
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 Server started. Listening on port 12345...

 Menu:
1. Add Employee
2. Delete Employee
3. Modify Employee
4. Display Employee Info
5. Exit

User Input: 1

Server: Employee added successfully.

Menu:
1. Add Employee
2. Delete Employee
3. Modify Employee
4. Display Employee Info
5. Exit

User Input: 4

Server: Employee info displayed.

Menu:
1. Add Employee
2. Delete Employee
3. Modify Employee
4. Display Employee Info
5. Exit

User Input: 5

Server: exit

 */