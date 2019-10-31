import java.net.*;  // for Socket
import java.io.*;   // for IOException and Input/OutputStream
import java.util.Scanner;

public class TCPEchoClient {

   public static void main(String[] args) throws IOException {
   
   
   
   /* 
   If they aren't in the DB, they are a new user,
   If they aren't in the DB with the other user, generate new primes
   Once you have the primes, you can start encrypting
   
   */
   
   
      if ((args.length < 2) || (args.length > 3))  // Test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
   
      String server = args[0];       // Server name or IP address
      
   
      // * Get server number from arguents
      int servPort = Integer.parseInt(args[1]);
   
      // Create socket that is connected to server on specified port
      Socket socket = new Socket(server, servPort);
      System.out.println("Connected to server...sending echo string");
   
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();
      
      // * Prompt user for string, convert to bytes
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter a sentence S");
      String input = scan.nextLine();
      byte[] byteBuffer = input.getBytes();
      
      // * Time variables
      long sentTime;
      long receiveTime;
      long timeMilli;
      
      
      // * Step 2: Repeatedly perform following actions
      while (input != "-1") {
      
         sentTime = System.currentTimeMillis();
         out.write(byteBuffer);  // Send the encoded string to the server
      
         // Receive the same string back from the server
         int totalBytesRcvd = 0;  // Total bytes received so far
         int bytesRcvd;           // Bytes received in last read
         while (totalBytesRcvd < byteBuffer.length) {
            if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,  
                               byteBuffer.length - totalBytesRcvd)) == -1)
               throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
         }
         
         // * Get round trip time
         receiveTime = System.currentTimeMillis();
         timeMilli = receiveTime - sentTime;
         
         System.out.println("Message Received: " + new String(byteBuffer) + "\n Round trip time in ms: " + timeMilli);
         
         
         System.out.println("Enter a sentence S");
         input = scan.nextLine();
      }
   
      socket.close();  // Close the socket and its streams
   }
}
