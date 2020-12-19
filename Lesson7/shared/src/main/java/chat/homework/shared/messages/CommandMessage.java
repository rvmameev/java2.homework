package chat.homework.shared.messages;

import chat.homework.shared.Command;
import java.io.Serializable;

public class CommandMessage implements Serializable
{
    private final Command command;

    public CommandMessage(Command command)
    {
        this.command = command;
    }

    public Command getCommand()
    {
        return command;
    }
}
