package chat.homework.client;

import chat.homework.shared.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static final int PORT = 8189;

    private final Viewable view;

    private String host;
    private int port;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private User user;

    public Client(Viewable view)
    {
        this.view = view;
    }

    public void Connect(String host, int port, User user)
    {
        this.host = host;
        this.port = port;
        this.user = user;

        new Thread(() -> socketHandler()).start();
    }

    public void sendMessage(String msg) {
        try {
            Message message = new Message(Command.SEND_TO_ALL, user, msg);

            out.writeUTF(message.serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void socketHandler()
    {
        try
        {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                try {
                    String str = in.readUTF();

                    Message message = Message.deserialize(str);

                    view.addMessage(message.getUser().getName() + " " + message.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
