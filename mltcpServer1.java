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

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
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

                    String serverResponse = "Server response: " + clientMessage.toUpperCase();
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

/*
Server started. Listening on port 12345...
New client connected: 127.0.0.1
Received from client: Hello
Received from client: World
Client disconnected: 127.0.0.1

 */