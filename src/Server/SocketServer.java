package Server;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SocketServer {
    DatagramSocket socket;
    int num = 5;
    int nr;

    public void init(int port) throws SocketException{
        socket = new DatagramSocket(port);
    }

    public void runServer() throws IOException{
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;
        String msg = "pon un número pendejito";

        while(true){
            //socket.send(new DatagramPacket(msg.getBytes(StandardCharsets.UTF_8), msg.length(), clientIP, clientPort));
            DatagramPacket packet = new DatagramPacket(receivingData, 1024);
            socket.receive(packet);
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
        nr = ByteBuffer.wrap(data).getInt();
        if(nr == num){
            return "ah verga, asertaste".getBytes(StandardCharsets.UTF_8);
        }
        else return "ah la cagaste".getBytes(StandardCharsets.UTF_8);
    }
}
