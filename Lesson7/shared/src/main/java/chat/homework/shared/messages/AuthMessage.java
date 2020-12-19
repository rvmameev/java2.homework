package chat.homework.shared.messages;

import chat.homework.shared.Command;

public class AuthMessage extends CommandMessage
{
    private final String login;
    private final String pass;

    public AuthMessage(String login, String pass)
    {
        super(Command.AUTH);

        this.login = login;
        this.pass = pass;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPass()
    {
        return pass;
    }
}
