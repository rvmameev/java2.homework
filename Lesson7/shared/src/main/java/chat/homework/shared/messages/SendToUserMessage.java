package chat.homework.shared.messages;

import chat.homework.shared.Command;
import chat.homework.shared.User;

public class SendToUserMessage extends SendToAllMessage
{
    private final String nickname;

    public SendToUserMessage(User fromUser, String toNickname, String message)
    {
        super(Command.SEND_TO_USER, fromUser, message);
        this.nickname = toNickname;
    }

    public String getNickname()
    {
        return nickname;
    }
}
