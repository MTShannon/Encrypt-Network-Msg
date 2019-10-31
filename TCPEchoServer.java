import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.io.*;   // for IOException and Input/OutputStream
import java.util.*;


public class TCPEchoServer {

   private static final int BUFSIZE = 32;   // Size of receive buffer

   public static void main(String[] args) throws IOException {
   
      if (args.length != 1)  // Test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Port>");
   
      int servPort = Integer.parseInt(args[0]);
   
      // Create a server socket to accept client connection requests
      ServerSocket servSock = new ServerSocket(servPort);
   
      int recvMsgSize;   // Size of received message
      byte[] byteBuffer = new byte[BUFSIZE];  // Receive buffer
   
      for (;;) { // Run forever, accepting and servicing connections
         Socket clntSock = servSock.accept();     // Get client connection
      
         System.out.println("Handling client at " +
            clntSock.getInetAddress().getHostAddress() + " on port " +
                 clntSock.getPort());
                 
         String clientIP = clntSock.getInetAddress().getHostAddress();
         int clientPort = clntSock.getPort();
      
         InputStream in = clntSock.getInputStream();
         OutputStream out = clntSock.getOutputStream();
         String message;
         
      
         // Receive until client closes connection, indicated by -1 return
         while ((recvMsgSize = in.read(byteBuffer)) != -1) {   
         // * printing info
            message = new String(byteBuffer);
            System.out.println("recvsMsgSize: " + recvMsgSize);
            System.out.println("Client IP: " + clientIP);
            System.out.println("Client Port: " + clientPort); 
            System.out.println("Client Message: " + message);
            System.out.println("Reversed Message: " + new String(reverseArray(byteBuffer)).repla);
            
            
            out.write(reverseArray(byteBuffer), 0, recvMsgSize);
         }
      
         clntSock.close();  // Close the socket.  We are done with this client!
      }
      /* NOT REACHED */
   }
   
   // * Reverse the byte array (message)
   public static byte[] reverseArray(byte[] array, messageSize) {
      for(int i = 0; i < array.length / 2; i++) {  
         byte temp = array[i];
         array[i] = array[array.length - i - 1];
         array[array.length - i - 1] = temp;
      }
      return array;
   }
}
