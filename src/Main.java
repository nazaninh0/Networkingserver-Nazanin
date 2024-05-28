// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client;
        int portnumber = 1234;

        if (args.length >= 1) {

            if (args.length >= 1) {

                portnumber = Integer.parseInt(args[0]);
            }

            // Create Server side socket
            try {
                server = new ServerSocket(portnumber);
            } catch (IOException ie) {
                System.out.println("Cannot open socket." + ie);
                System.exit(1);
            }

            System.out.println("ServerSocket is Created" + server);
            while (true) {
                try {
                    System.out.println("Waiting for connect request...");
                    client = server.accept();
                    System.out.println("");
                    String clientHost = client.getInetAddress().getHostAddress();
                    int clientPort = client.getPort();
                    System.out.println("Client host = " + clientHost + " Client port = " + clientPort);
                    InputStream clientIn = client.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                    String msgFromClient = br.readLine();
                    System.out.println("Message received from client = " + msgFromClient);
                    if (msgFromClient != null && !msgFromClient.equalsIgnoreCase("bye")) {
                        OutputStream clientOut = client.getOutputStream();
                        PrintWriter pw = new PrintWriter(clientOut, true);
                        String ansMsg = "Hello" + msgFromClient;
                        pw.println(ansMsg);
                        if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                            server.close();
                            client.close();
                            break;
                        }
                    }

                } catch (IOException ie) {

                }
            }
        }
    }
}