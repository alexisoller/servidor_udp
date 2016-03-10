/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class Udpserver {

    /**
     * @param args the command line arguments
     */
    private static final int PORT = 1234;
    private static String MACHINE = "localhost";

    public static void main(String[] args) {

        try {
            System.out.println("CREATING DATAGRAM SOCKET ON " + MACHINE + " Port:" + PORT);
            InetSocketAddress sockAddr = new InetSocketAddress(MACHINE, PORT);
            DatagramSocket datagramSocket = new DatagramSocket(sockAddr);
            System.out.println("WAITING FOR A MESSAGE ....");
            byte[] message = new byte[40];
            while(true){
            DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
            datagramSocket.receive(datagramPacket);
            client_thread cl = new client_thread(datagramSocket, datagramPacket);
            cl.start();
            }
            /*
             InetAddress senderAddr = datagramPacket.getAddress();
             int senderPort = datagramPacket.getPort();

             System.out.println("..........MESSAGE FROM ["
             + senderAddr.getHostName() + ","
             + senderPort + "] RECEIVED:" + new String(message));

             System.out.println("......SENDING RESPOSE TO SENDER...");

             String s = ">>>>>Message received OK<<<<<";
             byte[] messageBack = s.getBytes();
             DatagramPacket datagramPacketBack = new DatagramPacket(messageBack, messageBack.length, senderAddr, senderPort);

             datagramSocket.send(datagramPacketBack);

             System.out.println("RESPOSE SENT.CLOSING SOCKET");
             datagramSocket.close();
             System.out.println("SERVER FINISHED");*/
        } catch (SocketException ex) {
            Logger.getLogger(Udpserver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Udpserver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
