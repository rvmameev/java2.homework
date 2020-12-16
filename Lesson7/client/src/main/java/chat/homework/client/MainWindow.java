package chat.homework.client;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame
{
    public MainWindow() throws HeadlessException
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension dimWindow = new Dimension(600, 450);
        setSize(dimWindow);
        Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimScreen.width - dimWindow.width)/2, (dimScreen.height - dimWindow.height)/2);

        add(new MainPanel(this));

        setTitle("Чат");

        setVisible(true);
    }
}