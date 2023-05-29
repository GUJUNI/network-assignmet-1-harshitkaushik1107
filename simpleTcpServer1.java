/*Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Menu driven program to store employee details (like add,delete, modify, display info) using TCP
*/

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Start a new thread to handle client requests
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage;
            String serverResponse;

            // Display menu options to the client
            displayMenu();

            while ((clientMessage = in.readLine()) != null) {
                // Process client request
                serverResponse = processRequest(clientMessage);
                out.println(serverResponse);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {
        String menu = "Menu:\n" +
                "1. Add Employee\n" +
                "2. Delete Employee\n" +
                "3. Modify Employee\n" +
                "4. Display Employee Info\n" +
                "5. Exit\n";

        out.println(menu);
    }

    private String processRequest(String clientMessage) {
        // Process client request and return server response
        // You can implement the specific employee functions here based on the clientMessage

        // Sample implementation for each option:
        switch (clientMessage) {
            case "1":
                // Add Employee
                // Your implementation here
                return "Employee added successfully.";

            case "2":
                // Delete Employee
                // Your implementation here
                return "Employee deleted successfully.";

            case "3":
                // Modify Employee
                // Your implementation here
                return "Employee modified successfully.";

            case "4":
                // Display Employee Info
                // Your implementation here
                return "Employee info displayed.";

            case "5":
                // Exit
                return "exit";

            default:
                return "Invalid option. Please try again.";
        }
    }
}
