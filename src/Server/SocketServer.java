package Server;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SocketServer {
    DatagramSocket socket;

    public void init(int port) throws SocketException{
        socket = new DatagramSocket(port);
    }

    public void runServer() throws IOException{
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;

        while(true){
            DatagramPacket packet = new DatagramPacket(receivingData, 1024);
            socket.receive(packet);
            //System.out.println("rebut");
            sendingData = processData(packet.getData(), packet.getLength());
            clientIP = packet.getAddress();
            clientPort = packet.getPort();
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
            socket.send(packet);
        }
    }

    private byte[] processData(byte[] data, int length) {
        System.out.println(new String(data,0,length));
        return "rebut".getBytes(StandardCharsets.UTF_8);
    }
}
