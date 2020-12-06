package ru.geekbrains.java2.homework.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainPanel extends JPanel
{
    private TextArea textArea;
    private TextField inputField;
    private Button sendButton;

    public MainPanel()
    {
        super(new BorderLayout());

        initComponents();

        initListeners();
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
            addInputMessage();
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
                    addInputMessage();
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });
    }

    private void addInputMessage()
    {
        if (inputField.getText().trim().isEmpty())
            return;

        textArea.append(getPrefixTextRow() + inputField.getText() + System.lineSeparator());

        inputField.setText("");
        inputField.requestFocus();
    }

    private String getPrefixTextRow()
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " ";
    }
}
