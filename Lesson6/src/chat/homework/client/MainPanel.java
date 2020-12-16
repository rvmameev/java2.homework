package chat.homework.client;

import chat.homework.shared.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class MainPanel extends JPanel implements Viewable
{
    private TextArea textArea;
    private TextField inputField;
    private Button sendButton;

    private Client client;
    private User user;

    public MainPanel()
    {
        super(new BorderLayout());

        initComponents();

        initListeners();

        user = new User("User" + UUID.randomUUID().hashCode());

        client = new Client(this);

        client.Connect("localhost", Client.PORT, user);
    }

    private void initComponents()
    {
        textArea = new TextArea();
        Color color = textArea.getBackground();
        textArea.setEditable(false);
        textArea.setBackground(color);

        add(textArea, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new BorderLayout());

        add(bottomPanel, BorderLayout.SOUTH);

        inputField = new TextField();

        bottomPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new Button("Отправить");

        bottomPanel.add(sendButton, BorderLayout.EAST);
    }

    private void initListeners()
    {
        sendButton.addActionListener(e ->
        {
            sendMessage();
        });

        inputField.addKeyListener(new KeyListener()
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
        String message = inputField.getText().trim();

        if (message.isEmpty())
            return;

        client.sendMessage(message);

        inputField.setText("");
        inputField.requestFocus();
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
}
