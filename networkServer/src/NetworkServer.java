import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {
    private ServerSocket server;
    private DataInputStream dataInputStream;
    private static final int PORT = 3030;
    private static final String STOP_STRING = "##";


    public  NetworkServer() {
        try {
            server = new ServerSocket(PORT);
            iniConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniConnections() throws IOException {
        Socket clientSocket = server.accept();
        dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        readMessages();
        close();
    }

    private void close() throws IOException{
            dataInputStream.close();
            server.close();
    }
    private void readMessages() throws IOException {
        String line = "";
        while (!line.equals(STOP_STRING)){
            line = dataInputStream.readUTF();
            System.out.println(line);
        }
    }
    public static void main(String[] args) {
        System.out.println("Server listening on port: " + PORT);
        new NetworkServer();
    }

}
