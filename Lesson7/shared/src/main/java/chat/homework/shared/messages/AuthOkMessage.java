package chat.homework.shared.messages;

import chat.homework.shared.Command;
import chat.homework.shared.User;

public class AuthOkMessage extends CommandMessage
{
    private final User user;

    public AuthOkMessage(User user)
    {
        super(Command.AUTH_OK);
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }
}
