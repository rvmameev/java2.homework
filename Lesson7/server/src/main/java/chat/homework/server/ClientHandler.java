package chat.homework.server;

import chat.homework.shared.Command;
import chat.homework.shared.Serializer;
import chat.homework.shared.User;
import chat.homework.shared.messages.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    public void sendCommandMessage(CommandMessage commandMessage)
    {
        try
        {
            out.writeUTF(Serializer.serializeBase64(commandMessage));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public User getUser()
    {
        return user;
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

                CommandMessage commandMessage = Serializer.deserializeBase64(str);

                if (commandMessage.getCommand() == Command.LOGOUT)
                    break;

                switch (commandMessage.getCommand())
                {
                    case AUTH:
                    {
                        AuthMessage authMessage = (AuthMessage) commandMessage;

                        User user = SQLHandler.getUserByLoginAndPassword(authMessage.getLogin(), authMessage.getPass());

                        if (user != null)
                        {
                            sendCommandMessage(new AuthOkMessage(user));

                            server.subscribe(this);

                            this.user = user;
                        }

                        break;
                    }
                    case SEND_TO_USER:
                    {
                        SendToUserMessage sendToUserMessage = (SendToUserMessage)commandMessage;

                        server.sendMessage(sendToUserMessage);

                        break;
                    }
                    case SEND_TO_ALL:
                    {
                        SendToAllMessage sendToAllMessage = (SendToAllMessage)commandMessage;

                        server.broadcastMsg(sendToAllMessage);

                        break;
                    }
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.unsubscribe(this);
        }
    }

    private boolean isAuth()
    {
        return user != null;
    }
}
