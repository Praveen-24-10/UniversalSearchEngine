package gui;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private DefaultListModel<String> model;

    private JList<String> resultList;

    private JTextArea previewArea;

    public ResultPanel() {

        setLayout(
                new BorderLayout());

        model =
                new DefaultListModel<>();

        resultList =
                new JList<>(model);

        resultList.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        previewArea =
                new JTextArea();

        previewArea.setEditable(false);

        previewArea.setFont(
        new Font(
                "Segoe UI Emoji",
                Font.PLAIN,
                14));

        previewArea.setLineWrap(true);

        previewArea.setWrapStyleWord(true);

        JScrollPane resultScroll =
                new JScrollPane(
                        resultList);

        JScrollPane previewScroll =
                new JScrollPane(
                        previewArea);

        resultScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Search Results"));

        previewScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Preview"));

        JSplitPane splitPane =
                new JSplitPane(
                        JSplitPane.HORIZONTAL_SPLIT,
                        resultScroll,
                        previewScroll);

        splitPane.setDividerLocation(320);

        splitPane.setResizeWeight(0.35);

        add(
                splitPane,
                BorderLayout.CENTER);
    }

    public void clearResults() {

        model.clear();

        previewArea.setText("");
    }

    public void addResult(
            String result) {

        model.addElement(
                result);
    }

    public void setPreview(
            String text) {

        previewArea.setText(
                text);

        previewArea.setCaretPosition(
                0);
    }

    public JList<String> getResultList() {

        return resultList;
    }

    public JTextArea getPreviewArea() {

        return previewArea;
    }

    public DefaultListModel<String>
    getModel() {

        return model;
    }
}