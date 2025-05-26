/**
 * ChatClient.java
 * A simple chat client that connects to a ChatServer.
 * Allows users to send and receive broadcast messages via the server.
 *
 * @author Tatenda
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    /**
     * Connects to the chat server and starts input/output threads.
     * @throws IOException if connection to server fails
     */
    public void start() throws IOException {
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Connected to the chat server.");

        // Thread to read messages from the server
        new Thread(new ServerMessageReader()).start();

        // Read user input and send to server
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            output.println(message);
        }
    }

    /**
     * Inner class to read messages from the server.
     */
    private class ServerMessageReader implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = input.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        }
    }

    /**
     * Main method to run the chat client.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException e) {
            System.out.println("Failed to connect to server: " + e.getMessage());
        }
    }
}
