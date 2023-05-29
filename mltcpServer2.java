import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedTCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread clientThread = new ClientHandler(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage;
                while ((clientMessage = reader.readLine()) != null) {
                    System.out.println("Received from client: " + clientMessage);

                    // Parse the input message as a number
                    double number = Double.parseDouble(clientMessage);

                    // Calculate the square and square root
                    double square = number * number;
                    double squareRoot = Math.sqrt(number);

                    // Prepare the response message
                    String serverResponse = "Square: " + square + ", Square Root: " + squareRoot;

                    writer.println(serverResponse);
                }

                System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());

                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
