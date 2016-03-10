/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class client_thread extends Thread {

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    client_thread(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }

    @Override
    public void run() {
        try {

            InetAddress senderAddr = datagramPacket.getAddress();
            int senderPort = datagramPacket.getPort();

            System.out.println("..........MESSAGE FROM ["
                    + senderAddr.getHostName() + ","
                    + senderPort + "] RECEIVED:" + datagramPacket.toString());

            System.out.println("......SENDING RESPOSE TO SENDER...");
            Date date = new Date();
            String s = date.toString();
            byte[] messageBack = s.getBytes();
            DatagramPacket datagramPacketBack = new DatagramPacket(messageBack, messageBack.length, senderAddr, senderPort);

            datagramSocket.send(datagramPacketBack);
            
            
        } catch (IOException ex) {
            Logger.getLogger(client_thread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
