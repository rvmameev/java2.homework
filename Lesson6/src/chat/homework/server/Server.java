package chat.homework.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server
{
    public static final int PORT = 8189;

    private final Vector<ClientHandler> clients;
    private final int port;

    public Server()
    {
        this(PORT);
    }

    public Server(int port)
    {
        this.port = port;
        this.clients = new Vector<>();
    }

    public void Run()
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            while (true)
            {
                System.out.println("Waiting for client connection...");

                Socket socket = serverSocket.accept();

                System.out.println("Client connected " + socket);

                ClientHandler c = new ClientHandler(this, socket);

                subscribe(c);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }
    }
}
