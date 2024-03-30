import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage
    //
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    
    try {
      serverSocket = new ServerSocket(4221);
      serverSocket.setReuseAddress(true);
      clientSocket = serverSocket.accept(); // Wait for connection from client.
      System.out.println("accepted new connection");
      InputStream inputStream = clientSocket.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      String firstLine = br.readLine();
      String[] firstLineParts = firstLine.split(" ");
      String method = firstLineParts[0];
      String path = firstLineParts[1];
      if(path.equals("/")){
        clientSocket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
      }else{
        clientSocket.getOutputStream().write("HTTP/1.1 404 Not Found\r\n".getBytes(StandardCharsets.UTF_8));
      }
      System.out.println(path);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
