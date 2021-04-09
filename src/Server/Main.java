package Server;

import java.io.IOException;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws IOException {
        SocketServer socketServer = new SocketServer();

        socketServer.init(5555);
        socketServer.runServer();
    }
}
