package chat.homework.server;

import chat.homework.shared.*;
import java.io.*;
import java.net.Socket;

public class ClientHandler
{
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private User user;

    public ClientHandler(Server server, Socket socket)
    {
        this.server = server;
        this.socket = socket;

        new Thread(() -> handler()).start();
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handler()
    {
        try
        {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true)
            {
                String str = in.readUTF();

                Message message = Message.deserialize(str);

                user = message.getUser();

                Command command = message.getCommand();

                if (str.equals(command == Command.DISCONNECT))
                    break;

                switch (command)
                {
                    case SEND_TO_ALL:
                        server.broadcastMsg(str);
                }
            }

            server.unsubscribe(this);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public User getUser()
    {
        return user;
    }
}
