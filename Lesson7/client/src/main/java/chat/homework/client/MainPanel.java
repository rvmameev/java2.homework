package chat.homework.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPanel extends JPanel implements Viewable
{
    private final JFrame window;

    private JTextArea textArea;

    private JPanel messagePanel;
    private JTextField messageField;
    private JButton sendButton;

    private JPanel authPanel;
    private JTextField loginField;
    private JPasswordField passField;
    private JButton authButton;

    private Client client;

    private boolean isAuth;

    public MainPanel(JFrame window)
    {
        super(new BorderLayout());

        this.window = window;

        initComponents();

        initListeners();

        isAuth = false;

        client = new Client(this);
    }

    private void initComponents()
    {
        textArea = new JTextArea();
        Color color = textArea.getBackground();
        textArea.setEditable(false);
        textArea.setBackground(color);

        authPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField();
        passField = new JPasswordField();
        authButton = new JButton("Подключиться");

        panel.add(loginField);
        panel.add(passField);

        authPanel.add(panel, BorderLayout.CENTER);
        authPanel.add(authButton, BorderLayout.EAST);

        messagePanel = new JPanel(new BorderLayout());

        messageField = new JTextField();
        sendButton = new JButton("Отправить");

        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        add(authPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);

        setAuth(false);
    }

    private void initListeners()
    {
        window.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                client.logout();

                super.windowClosing(e);
            }
        });

        authButton.addActionListener(e ->
        {
            client.login(loginField.getText(), new String(passField.getPassword()));
        });

        sendButton.addActionListener(e ->
        {
            sendMessage();
        });

        messageField.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    sendMessage();
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });
    }

    private void sendMessage()
    {
        String message = messageField.getText().trim();

        if (message.isEmpty())
            return;

        String[] tokens = message.split(" ");

        if (tokens.length > 2 && tokens[0].equals("/w"))
        {
            String nickname = tokens[1];

            message = message.substring(3 + nickname.length() + 1);

            client.sendToNickname(nickname, message);
        }
        else
        {
            client.sendToAllMessage(message);
        }

        messageField.setText("");
        messageField.requestFocus();
    }

    private String getPrefixTextRow()
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " ";
    }

    @Override
    public void addMessage(String message)
    {
        textArea.append(getPrefixTextRow() + message + System.lineSeparator());
    }

    @Override
    public void setAuth(boolean auth)
    {
        this.isAuth = auth;

        authPanel.setVisible(!auth);
        messagePanel.setVisible(auth);
    }
}
