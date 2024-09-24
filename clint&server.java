// Server.java
import java.io.*;
import java.net.*;
//Imports: Import necessary Java packages for networking and input/output operations.
public class Server {
//Server Class: Define the Server class.
 public static void main(String[] args) throws IOException {
//main Method: The starting point of execution for the server application.
 ServerSocket serverSocket = new ServerSocket(5000);
//ServerSocket: Create a ServerSocket that listens on port 5000.
 System.out.println("Server started. Waiting for a client ...");
//Console Output: Print a message indicating that the server has started and is waiting for a client.
 Socket socket = serverSocket.accept();
//Client Connection: Wait for a client to connect. When a connection is established, accept it and create a Socket
for communication.
 System.out.println("Client accepted.");
//Console Output: Print a message indicating that a client has been accepted.
 DataInputStream in = new DataInputStream(new 
BufferedInputStream(socket.getInputStream()));
 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//Input/Output Streams: Create input and output streams to receive and send data to the connected client.
 String line = "";
 while (!line.equals("Over")) {
//Communication Loop: Enter a loop that continues until the client sends the message "Over."
 try {
 line = in.readUTF();
//Read Client Input: Read the input from the client.
 String[] parts = line.split(" ");
double result = 0;
//Parse Input: Split the input into parts, and initialize a variable to store the result of mathematical 
operations.
 switch (parts[0]) {
 case "add":
 result = Double.parseDouble(parts[1]) + 
Double.parseDouble(parts[2]);
 break;
 case "sub":
 result = Double.parseDouble(parts[1]) -
Double.parseDouble(parts[2]);
 break;
 case "mul":
 result = Double.parseDouble(parts[1]) * 
Double.parseDouble(parts[2]);
 break;
 case "div":
 result = Double.parseDouble(parts[1]) / 
Double.parseDouble(parts[2]);
 break;
 case "sqrt":
 result = Math.sqrt(Double.parseDouble(parts[1]));
 break;
 case "pow":
 result = Math.pow(Double.parseDouble(parts[1]), 
Double.parseDouble(parts[2]));
 break;
 case "sin":
 result = Math.sin(Double.parseDouble(parts[1]));
 break;
 case "cos":
 result = Math.cos(Double.parseDouble(parts[1]));
 break;
 case "tan":
 result = Math.tan(Double.parseDouble(parts[1]));
 break;
 case "log":
 result = Math.log(Double.parseDouble(parts[1]));
 break;
 }
// Switch Statement: Evaluate the operation requested by the client and perform the corresponding 
mathematical operation.
 out.writeUTF(Double.toString(result));
// Send Result: Send the result back to the client.
 } catch (IOException i) {
 System.out.println(i);
 }
// Exception Handling: Handle exceptions related to input/output operations.
 }
 System.out.println("Closing connection");
 socket.close();
 in.close();
 }
// Connection Closure: Print a closing connection message and close the socket and input stream.
}
// Client.java
import java.net.*;
import java.io.*;
// Imports: Import necessary Java packages for networking and input/output operations.
public class Client {
// Client Class: Define the Client class.
 public static void main(String[] args) throws IOException {
// main Method: The starting point of execution for the client application.
 Socket socket = new Socket("127.0.0.1", 5000);
// Socket Initialization: Create a socket and connect to the server at IP address "127.0.0.1" (localhost) and port 5000.
 System.out.println("Connected");
// Console Output: Print a message indicating that the client has successfully connected.
 BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
 DataInputStream in = new DataInputStream(new 
BufferedInputStream(socket.getInputStream()));
 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
// Input/Output Streams: Initialize input and output streams for communication with the server.
 String line = "";
 while (!line.equals("Over")) {
// Communication Loop: Enter a loop that continues until the user enters "Over."
 try {
 line = input.readLine();
// Read User Input: Read input from the user.
 out.writeUTF(line);
// Send Input to Server: Send the user's input to the server.
 String result = in.readUTF();
 System.out.println("Result: " + result);
// Receive and Print Result: Receive the result from the server and print it.
 } catch (IOException i) {
 System.out.println(i);
 }
// Exception Handling: Handle exceptions related to input/output operations.
 }
 System.out.println("Closing connection");
 socket.close();
 input.close();
 }
// Connection Closure: Print a closing connection message and close the socket and input stream.
}