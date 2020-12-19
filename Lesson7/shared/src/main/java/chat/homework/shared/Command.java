package chat.homework.shared;

import java.io.Serializable;

public enum Command implements Serializable
{
    AUTH("/auth"),
    AUTH_OK("/auth_ok"),
    SEND_TO_USER("/send_to_user"),
    SEND_TO_ALL("/send_to_all"),
    LOGOUT("/end");

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
