import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NetworkClient {
    private Socket socket;
    private static final int PORT = 3030;
    private DataOutputStream dataOutputStream;
    private static final String STOP_STRING = "##";
    private Scanner in;


    public NetworkClient() {
        try {
            socket = new Socket("127.0.0.1", PORT);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            in = new Scanner(System.in);
            writeMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeMessages() throws IOException {
        String line = "";
        while (!line.equals(STOP_STRING)) {
            line = in.nextLine();
            dataOutputStream.writeUTF(line);
        }
        close();
    }
    private void close() throws IOException{
        socket.close();
        dataOutputStream.close();
        in.close();
    }
    public static void main(String[] args) {
        System.out.println("Client listening to server on port: " + PORT);
        new NetworkClient();
    }

}
