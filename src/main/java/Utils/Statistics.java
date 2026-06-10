package Utils;

import java.io.File;

public class Statistics {

    private static int totalFiles;
    private static int txtFiles;
    private static int pdfFiles;
    private static int docxFiles;
    private static int csvFiles;
    private static int javaFiles;

    public static String getStatistics(
            File folder) {

        totalFiles = 0;
        txtFiles = 0;
        pdfFiles = 0;
        docxFiles = 0;
        csvFiles = 0;
        javaFiles = 0;

        scan(folder);

        return
                "========== FILE STATISTICS ==========\n\n"

                        + "Total Files : "
                        + totalFiles

                        + "\n\nTXT Files  : "
                        + txtFiles

                        + "\nPDF Files  : "
                        + pdfFiles

                        + "\nDOCX Files : "
                        + docxFiles

                        + "\nCSV Files  : "
                        + csvFiles

                        + "\nJAVA Files : "
                        + javaFiles;
    }

    private static void scan(
            File folder) {

        File[] files =
                folder.listFiles();

        if (files == null) {

            return;
        }

        for (File file : files) {

            if (file.isDirectory()) {

                scan(file);
            }

            else {

                totalFiles++;

                String name =
                        file.getName()
                                .toLowerCase();

                if (name.endsWith(".txt")) {

                    txtFiles++;
                }

                else if (name.endsWith(".pdf")) {

                    pdfFiles++;
                }

                else if (name.endsWith(".docx")) {

                    docxFiles++;
                }

                else if (name.endsWith(".csv")) {

                    csvFiles++;
                }

                else if (name.endsWith(".java")) {

                    javaFiles++;
                }
            }
        }
    }
}