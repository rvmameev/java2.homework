package chat.homework.client;

import chat.homework.shared.Command;
import chat.homework.shared.Serializer;
import chat.homework.shared.User;
import chat.homework.shared.messages.*;

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

    public void connect(String host, int port)
    {
        this.host = host;
        this.port = port;

        new Thread(() -> socketHandler()).start();
    }

    public void connect()
    {
        connect("localhost", PORT);
    }

    public void login(String login, String pass)
    {
        connect();

        sendCommandMessage(new AuthMessage(login, pass));
    }

    public void logout()
    {
        sendCommandMessage(new CommandMessage(Command.LOGOUT));

        user = null;
        view.setAuth(false);
    }

    public void sendToNickname(String nickname, String msg)
    {
        sendCommandMessage(new SendToUserMessage(user, nickname, msg));
    }

    public void sendToAllMessage(String msg)
    {
        sendCommandMessage(new SendToAllMessage(user, msg));
    }

    private void socketHandler()
    {
        if (isConnected())
            return;

        try
        {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true)
            {
                try
                {
                    String str = in.readUTF();

                    CommandMessage commandMessage = Serializer.deserializeBase64(str);

                    switch (commandMessage.getCommand())
                    {
                        case AUTH_OK:
                        {
                            AuthOkMessage authOkMessage = (AuthOkMessage) commandMessage;

                            user = authOkMessage.getUser();

                            view.setAuth(true);

                            break;
                        }
                        case SEND_TO_USER:
                        {
                            SendToUserMessage sendToUserMessage = (SendToUserMessage)commandMessage;

                            String from = sendToUserMessage.getUser().getNickname();
                            String to = sendToUserMessage.getNickname();

                            view.addMessage(from + " -> " + to + "  " + sendToUserMessage.getMessage());

                            break;
                        }
                        case SEND_TO_ALL:
                        {
                            SendToAllMessage sendToAllMessage = (SendToAllMessage) commandMessage;

                            view.addMessage(sendToAllMessage.getUser().getNickname() + " -> All   " + sendToAllMessage.getMessage());

                            break;
                        }
                    }
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                out.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            user = null;
            view.setAuth(false);
        }
    }

    private boolean isConnected()
    {
        return socket != null && socket.isConnected();
    }

    private boolean isAuth()
    {
        return user != null;
    }

    private void sendCommandMessage(CommandMessage commandMessage)
    {
        try
        {
            if (!isConnected())
                return;

            out.writeUTF(Serializer.serializeBase64(commandMessage));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
