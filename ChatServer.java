/**
 * ChatServer class handles incoming connections from multiple clients,
 * assigns unique user IDs, and broadcasts messages to all clients.
 * 
 * @author Tatenda
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();
    private static int userIdCounter = 1;

    /**
     * Main method to start the chat server and accept client connections.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Chat server started on port " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, userIdCounter++);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    /**
     * Broadcasts a message to all connected clients.
     * @param message The message to broadcast
     */
    public static void broadcastMessage(String message) {
        for (ClientHandler handler : clientHandlers) {
            handler.sendMessage(message);
        }
    }

    /**
     * Removes a client handler from the set of connected clients.
     * @param handler The client handler to remove
     */
    public static void removeClient(ClientHandler handler) {
        clientHandlers.remove(handler);
    }

    /**
     * Inner class to handle communication with an individual client.
     */
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private int userId;

        /**
         * Constructs a new ClientHandler.
         * @param socket The client's socket
         * @param userId A unique ID for the client
         */
        public ClientHandler(Socket socket, int userId) {
            this.socket = socket;
            this.userId = userId;
            try {
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.out.println("Error setting up client I/O: " + e.getMessage());
            }
        }

        /**
         * Sends a message to the client.
         * @param message The message to send
         */
        public void sendMessage(String message) {
            out.println(message);
        }

        /**
         * The main run method to read and broadcast messages.
         */
        @Override
        public void run() {
            try {
                out.println("Welcome User" + userId);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    ChatServer.broadcastMessage("User" + userId + ": " + inputLine);
                }
            } catch (IOException e) {
                System.out.println("Connection with User" + userId + " lost.");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
                ChatServer.removeClient(this);
            }
        }
    }
}
