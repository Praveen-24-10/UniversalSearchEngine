package gui;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    private JLabel statusLabel;

    public StatusBar() {

        setLayout(
                new BorderLayout());

        statusLabel =
                new JLabel("Ready");

        add(
                statusLabel,
                BorderLayout.WEST);
    }

    public void setStatus(
            String message) {

        statusLabel.setText(
                message);
    }
}