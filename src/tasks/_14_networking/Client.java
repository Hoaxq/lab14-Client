package tasks._14_networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String line = "";

        while (!line.equalsIgnoreCase("exit")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static void main (String[] args) {
        new Client("localhost", 10000);
    }
}