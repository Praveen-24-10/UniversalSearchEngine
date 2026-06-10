package gui;

import Search.DuplicateFinder;
import Utils.Exporter;
import Utils.RecentSearches;
import Utils.Statistics;

import javax.swing.*;
import java.awt.Desktop;
import java.awt.Window;
import java.io.File;

public class MenuBarPanel extends JMenuBar {

    private static boolean darkMode = false;

    public MenuBarPanel() {

        JMenu fileMenu =
                new JMenu("File");

        JMenu toolsMenu =
                new JMenu("Tools");

        JMenu helpMenu =
                new JMenu("Help");

        JMenuItem exportItem =
                new JMenuItem(
                        "Export Results");

        JMenuItem openFolderItem =
                new JMenuItem(
                        "Open Documents Folder");

        JMenuItem exitItem =
                new JMenuItem(
                        "Exit");

        JMenuItem darkModeItem =
                new JMenuItem(
                        "Toggle Dark Mode");

        JMenuItem recentItem =
                new JMenuItem(
                        "Recent Searches");

        JMenuItem statsItem =
                new JMenuItem(
                        "Statistics");

        JMenuItem duplicateItem =
                new JMenuItem(
                        "Duplicate Finder");

        JMenuItem aboutItem =
                new JMenuItem(
                        "About");

        // File Menu
        fileMenu.add(exportItem);
        fileMenu.add(openFolderItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Tools Menu
        toolsMenu.add(darkModeItem);
        toolsMenu.add(recentItem);
        toolsMenu.add(statsItem);
        toolsMenu.add(duplicateItem);

        // Help Menu
        helpMenu.add(aboutItem);

        add(fileMenu);
        add(toolsMenu);
        add(helpMenu);

        // Export Results
        exportItem.addActionListener(e -> {

            Exporter.exportResults(
                    SearchPanel.getLastResults());

            JOptionPane.showMessageDialog(
                    null,
                    "Results exported successfully to\nsearch_results.txt",
                    "Export Complete",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Open Documents Folder
        openFolderItem.addActionListener(e -> {

            try {

                Desktop.getDesktop().open(
                        new File("Documents"));

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Cannot open Documents folder.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Exit
        exitItem.addActionListener(e -> {

            System.exit(0);
        });

        // Dark Mode
        darkModeItem.addActionListener(e -> {

            darkMode = !darkMode;

            try {

                if (darkMode) {

                    UIManager.setLookAndFeel(
                            "javax.swing.plaf.nimbus.NimbusLookAndFeel");

                } else {

                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                }

                Window window =
                        SwingUtilities.getWindowAncestor(this);

                if (window != null) {

                    SwingUtilities.updateComponentTreeUI(
                            window);
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // Recent Searches
        recentItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    null,
                    RecentSearches.getSearchHistory(),
                    "Recent Searches",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Statistics
        statsItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    null,
                    Statistics.getStatistics(
                            new File("Documents")),
                    "File Statistics",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Duplicate Finder
        duplicateItem.addActionListener(e -> {

            String duplicates =
                    DuplicateFinder.getDuplicates(
                            new File("Documents"));

            JTextArea area =
                    new JTextArea(
                            duplicates);

            area.setEditable(false);

            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JScrollPane scrollPane =
                    new JScrollPane(area);

            scrollPane.setPreferredSize(
                    new java.awt.Dimension(
                            700,
                            400));

            JOptionPane.showMessageDialog(
                    null,
                    scrollPane,
                    "Duplicate Finder",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // About
        aboutItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    null,
                    "Universal Search Engine\n\n"
                            + "Version : 1.0\n"
                            + "Built using Java Swing\n\n"
                            + "Features:\n"
                            + "• Content Search\n"
                            + "• TXT Search\n"
                            + "• PDF Search\n"
                            + "• DOCX Search\n"
                            + "• CSV Search\n"
                            + "• Search Filters\n"
                            + "• Recent Searches\n"
                            + "• Export Results\n"
                            + "• Statistics\n"
                            + "• Duplicate Finder\n"
                            + "• Dark Mode",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
}