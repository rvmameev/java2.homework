package chat.homework.server;

import chat.homework.shared.User;
import chat.homework.shared.messages.CommandMessage;
import chat.homework.shared.messages.SendToAllMessage;
import chat.homework.shared.messages.SendToUserMessage;

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
        SQLHandler.connect();

        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            while (true)
            {
                System.out.println("Waiting for client connection...");

                Socket socket = serverSocket.accept();

                System.out.println("Client connected " + socket);

                ClientHandler c = new ClientHandler(this, socket);
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

    public void broadcastMsg(CommandMessage commandMessage) {
        for (ClientHandler c : clients) {
            c.sendCommandMessage(commandMessage);
        }
    }

    public void sendMessage(SendToUserMessage sendToUserMessage)
    {
        clients.forEach(c ->
        {
            User user = c.getUser();

            if (user != null &&
                (user.getNickname().equals(sendToUserMessage.getNickname()))
                || user.equals(sendToUserMessage.getUser()))
                c.sendCommandMessage(sendToUserMessage);
        });
    }
}
