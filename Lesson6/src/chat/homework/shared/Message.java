package chat.homework.shared;

import java.io.Serializable;

public class Message implements Serializable
{
    private final Command command;
    private final User user;
    private final String message;

    public Message(Command command, User user, String message)
    {
        if (command == null)
            throw new IllegalArgumentException("command");

        if (user == null)
            throw new IllegalArgumentException("user");

        if (message == null)
            throw new IllegalArgumentException("message");

        this.command = command;
        this.user = user;
        this.message = message;
    }

    public Message(Command command, User user)
    {
        this(command, user, "");
    }

    public Message(Command command)
    {
        this(command, new User(""), "");
    }

    public String serialize()
    {
        return Serializer.serialize(this);
    }

    public static Message deserialize(String str)
    {
        return (Message) Serializer.deserialize(str);
    }

    public Command getCommand()
    {
        return command;
    }

    public User getUser()
    {
        return user;
    }

    public String getMessage()
    {
        return message;
    }
}
