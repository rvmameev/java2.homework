package ru.geekbrains.java2.homework.lesson4;

import javax.swing.*;

public class App
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new MainWindow());

        System.out.println("App is running...");
    }
}
