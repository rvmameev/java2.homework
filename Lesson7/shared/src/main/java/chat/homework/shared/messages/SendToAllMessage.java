package chat.homework.shared.messages;

import chat.homework.shared.Command;
import chat.homework.shared.User;

public class SendToAllMessage extends CommandMessage
{
    private final User user;
    private final String message;

    public SendToAllMessage(User user, String message)
    {
        this(Command.SEND_TO_ALL, user, message);
    }

    protected SendToAllMessage(Command command, User user, String message)
    {
        super(command);

        this.user = user;

        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public User getUser()
    {
        return user;
    }
}
