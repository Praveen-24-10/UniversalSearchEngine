package gui;

import Search.ContentSearcher;
import Search.SearchResult;

import Utils.FileOpener;
import Utils.RecentSearches;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class SearchPanel extends JPanel {

    private JTextField searchField;

    private JButton searchButton;
    private JButton openButton;

    private JCheckBox txtBox;
    private JCheckBox pdfBox;
    private JCheckBox docxBox;
    private JCheckBox csvBox;
    private JCheckBox javaBox;

    private ResultPanel resultPanel;

    private ArrayList<SearchResult> currentResults =
            new ArrayList<>();

    private static ArrayList<SearchResult> lastResults =
            new ArrayList<>();

    public SearchPanel() {

        setLayout(new BorderLayout());

        JPanel topPanel =
                new JPanel();

        searchField =
                new JTextField(30);

        searchButton =
                new JButton("Search");

        openButton =
                new JButton("Open Selected");

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(openButton);

        JPanel filterPanel =
                new JPanel();

        txtBox =
                new JCheckBox("TXT", true);

        pdfBox =
                new JCheckBox("PDF", true);

        docxBox =
                new JCheckBox("DOCX", true);

        csvBox =
                new JCheckBox("CSV", true);

        javaBox =
                new JCheckBox("JAVA", true);

        filterPanel.add(txtBox);
        filterPanel.add(pdfBox);
        filterPanel.add(docxBox);
        filterPanel.add(csvBox);
        filterPanel.add(javaBox);

        JPanel northPanel =
                new JPanel(
                        new BorderLayout());

        northPanel.add(
                topPanel,
                BorderLayout.NORTH);

        northPanel.add(
                filterPanel,
                BorderLayout.SOUTH);

        add(
                northPanel,
                BorderLayout.NORTH);

        resultPanel =
                new ResultPanel();

        add(
                resultPanel,
                BorderLayout.CENTER);

        searchButton.addActionListener(e -> {

            long startTime =
                    System.currentTimeMillis();

            String keyword =
                    searchField.getText()
                            .trim()
                            .toLowerCase();

            if (keyword.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a keyword.");

                return;
            }

            RecentSearches.addSearch(
                    keyword);

            Window window =
                    SwingUtilities.getWindowAncestor(this);

            if (window instanceof SearchFrame) {

                ((SearchFrame) window)
                        .getStatusBar()
                        .setStatus("Searching...");
            }

            resultPanel.clearResults();

            ContentSearcher.clearResults();

            ContentSearcher.search(
                    new File("Documents"),
                    keyword);

            currentResults =
                    ContentSearcher.getRankedResults();

            lastResults =
                    new ArrayList<>(
                            currentResults);

            int displayedCount = 0;

            for (SearchResult result :
                    currentResults) {

                if (!allowedFileType(
                        result.getFile()
                                .getName())) {

                    continue;
                }

                displayedCount++;

                String fileName =
                        result.getFile()
                                .getName();

                String icon = "[TXT]";

                if (fileName.toLowerCase().endsWith(".pdf")) {

                    icon = "[PDF]";
                }

                else if (fileName.toLowerCase().endsWith(".docx")) {

                    icon = "[DOCX]";
                }

                else if (fileName.toLowerCase().endsWith(".csv")) {

                    icon = "[CSV]";
                }

                else if (fileName.toLowerCase().endsWith(".java")) {

                    icon = "[JAVA]";
                }

                resultPanel.addResult(
                        icon + " "
                                + fileName
                                + " | Matches: "
                                + result.getMatches());
            }

            if (displayedCount == 0) {

                resultPanel.addResult(
                        "No results found.");

                if (window instanceof SearchFrame) {

                    ((SearchFrame) window)
                            .getStatusBar()
                            .setStatus(
                                    "No results found");
                }

                return;
            }

            long endTime =
                    System.currentTimeMillis();

            if (window instanceof SearchFrame) {

                ((SearchFrame) window)
                        .getStatusBar()
                        .setStatus(
                                displayedCount
                                        + " results found | Search Time: "
                                        + (endTime - startTime)
                                        + " ms");
            }
        });

        openButton.addActionListener(e -> {

            int index =
                    resultPanel.getResultList()
                            .getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a file.");

                return;
            }

            ArrayList<SearchResult> filtered =
                    getFilteredResults();

            if (index >= filtered.size()) {

                return;
            }

            SearchResult selected =
                    filtered.get(index);

            FileOpener.openFile(
                    selected.getFile()
                            .getAbsolutePath());

            Window window =
                    SwingUtilities.getWindowAncestor(this);

            if (window instanceof SearchFrame) {

                ((SearchFrame) window)
                        .getStatusBar()
                        .setStatus(
                                "Opened "
                                        + selected.getFile()
                                        .getName());
            }
        });

        resultPanel.getResultList()
                .addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseClicked(
                                    MouseEvent e) {

                                if (e.getClickCount() == 2) {

                                    int index =
                                            resultPanel
                                                    .getResultList()
                                                    .getSelectedIndex();

                                    ArrayList<SearchResult>
                                            filtered =
                                            getFilteredResults();

                                    if (index < 0
                                            || index >= filtered.size()) {

                                        return;
                                    }

                                    SearchResult selected =
                                            filtered.get(index);

                                    FileOpener.openFile(
                                            selected.getFile()
                                                    .getAbsolutePath());
                                }
                            }
                        });

        resultPanel.getResultList()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int index =
                                resultPanel
                                        .getResultList()
                                        .getSelectedIndex();

                        ArrayList<SearchResult>
                                filtered =
                                getFilteredResults();

                        if (index >= 0
                                && index < filtered.size()) {

                            SearchResult result =
                                    filtered.get(index);

                            String preview =
                                    result.getPreview();

                            String keyword =
                                    searchField.getText()
                                            .trim();

                            if (!keyword.isEmpty()) {

                                preview =
                                        preview.replaceAll(
                                                "(?i)"
                                                        + java.util.regex.Pattern
                                                        .quote(keyword),

                                                "[" + keyword.toUpperCase() + "]");
                            }

                            String fileName =
                                    result.getFile()
                                            .getName();

                            String icon = "[TXT]";

                            if (fileName.toLowerCase().endsWith(".pdf")) {

                                icon = "[PDF]";
                            }

                            else if (fileName.toLowerCase().endsWith(".docx")) {

                                icon = "[DOCX]";
                            }

                            else if (fileName.toLowerCase().endsWith(".csv")) {

                                icon = "[CSV]";
                            }

                            else if (fileName.toLowerCase().endsWith(".java")) {

                                icon = "[JAVA]";
                            }

                            resultPanel.setPreview(

                                    "========================================\n"
                                            + icon + " "
                                            + fileName + "\n"
                                            + "========================================\n\n"

                                            + "Matches : "
                                            + result.getMatches()

                                            + "\n\n"

                                            + "Preview\n"
                                            + "----------------------------------------\n"

                                            + preview

                                            + "\n\n"

                                            + "Path\n"
                                            + "----------------------------------------\n"

                                            + result.getFile()
                                            .getAbsolutePath()
                            );
                        }
                    }
                });
    }

    private boolean allowedFileType(
            String fileName) {

        fileName =
                fileName.toLowerCase();

        if (fileName.endsWith(".txt"))
            return txtBox.isSelected();

        if (fileName.endsWith(".pdf"))
            return pdfBox.isSelected();

        if (fileName.endsWith(".docx"))
            return docxBox.isSelected();

        if (fileName.endsWith(".csv"))
            return csvBox.isSelected();

        if (fileName.endsWith(".java"))
            return javaBox.isSelected();

        return false;
    }

    private ArrayList<SearchResult>
    getFilteredResults() {

        ArrayList<SearchResult> filtered =
                new ArrayList<>();

        for (SearchResult result :
                currentResults) {

            if (allowedFileType(
                    result.getFile()
                            .getName())) {

                filtered.add(result);
            }
        }

        return filtered;
    }

    public static ArrayList<SearchResult>
    getLastResults() {

        return lastResults;
    }
}