package chat.homework.shared;

import java.io.Serializable;

public enum Command implements Serializable
{
    SEND_TO_ALL("/send_to_all"),
    DISCONNECT("/end");

    private final String command;

    Command(String command)
    {
        this.command = command;
    }

    public String getCommand()
    {
        return command;
    }
}
