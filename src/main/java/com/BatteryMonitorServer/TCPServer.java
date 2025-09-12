package com.BatteryMonitorServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPServer {

    private PythonOuput ouput;

    TCPServer( Device device ) {
        ouput = new PythonOuput( device );
    } // end TCPServer

    public void run() throws IOException {
        String inputLine = "";
        String battery = "";
        String token = "";
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2453);
            System.out.println("Server is listening");
            serverSocket.setSoTimeout(20000); // wait for 20 sec
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


                int count = 0;
                while ((inputLine = in.readLine()) != null) {
                    if ( count == 0 ) {
                        battery = inputLine;
                    } // if()
                    else {
                        token = inputLine;
                    } // else()

                    count = count+1;
                } // end while()

                // System.out.println(token);
                ouput.print_device_info( battery, token );

                clientSocket.close();
            }
        } catch (SocketTimeoutException e) {
            System.out.println("It's time up. No more listen client.");
        } finally {
            if (serverSocket != null) serverSocket.close();
        }

    } // end sendMessage()
}
