package chat.homework.shared;

import java.io.Serializable;

public class User implements Serializable
{
    private final String name;

    public User(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("name");

        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
