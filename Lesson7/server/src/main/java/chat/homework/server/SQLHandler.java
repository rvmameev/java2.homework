package chat.homework.server;

import chat.homework.shared.User;

import java.sql.*;

public class SQLHandler
{
    private static Connection connection;
    private static Statement statement;

    public static void connect()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:server/database.db");
            statement = connection.createStatement();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void disconnect()
    {
        try
        {
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static User getUserByLoginAndPassword(String login, String password)
    {
        try
        {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login ='" + login + "' AND password = '" + password + "'");

            if (rs.next())
            {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("nickname"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUserByNickname(String nickname)
    {
        try
        {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE nickname ='" + nickname + "'");

            if (rs.next())
            {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("nickname"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
