package chat.homework.shared;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable
{
    private final int id;
    private final String login;
    private final String nickname;

    public User(int id, String login, String name)
    {
        if (login == null)
            throw new IllegalArgumentException("name");

        this.id = id;
        this.login = login;
        this.nickname = name;
    }

    public int getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getNickname()
    {
        return nickname;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
            Objects.equals(login, user.login) &&
            Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, login, nickname);
    }
}
