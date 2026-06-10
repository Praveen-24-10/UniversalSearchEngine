package gui;

import javax.swing.*;
import java.awt.*;

public class SearchFrame extends JFrame {

    private StatusBar statusBar;

    public SearchFrame() {

        setTitle("Universal Search Engine");

        setSize(1000, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        setJMenuBar(
                new MenuBarPanel());

        SearchPanel searchPanel =
                new SearchPanel();

        add(
                searchPanel,
                BorderLayout.CENTER);

        statusBar =
                new StatusBar();

        add(
                statusBar,
                BorderLayout.SOUTH);

        setVisible(true);
    }

    public StatusBar getStatusBar() {

        return statusBar;
    }
}