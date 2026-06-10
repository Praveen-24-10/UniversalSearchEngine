package Search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Readers.TxtReader;
import Readers.CsvReader;
import Readers.PdfReader;
import Readers.DocxReader;

public class ContentSearcher {

    private static ArrayList<File> resultFiles =
            new ArrayList<>();

    private static ArrayList<SearchResult> rankedResults =
            new ArrayList<>();

    public static void clearResults() {

        resultFiles.clear();
        rankedResults.clear();
    }

    public static ArrayList<File> getResults() {

        return resultFiles;
    }

    public static ArrayList<SearchResult> getRankedResults() {

        return rankedResults;
    }

    public static void search(
            File folder,
            String keyword) {

        File[] files = folder.listFiles();

        if (files == null)
            return;

        for (File file : files) {

            if (file.isDirectory()) {

                search(file, keyword);
            }

            else {

                String name =
                        file.getName()
                                .toLowerCase();

                boolean found = false;

                int count = 0;
                String preview = "";

                // TXT / JAVA
                if (name.endsWith(".txt")
                        || name.endsWith(".java")) {

                    found =
                            TxtReader.contains(
                                    file,
                                    keyword);

                    if (found) {

                        count =
                                TxtReader.countMatches(
                                        file,
                                        keyword);

                        preview =
                                TxtReader.getPreview(
                                        file,
                                        keyword);
                    }
                }

                // CSV
                else if (name.endsWith(".csv")) {

                    found =
                            CsvReader.contains(
                                    file,
                                    keyword);

                    if (found) {

                        count =
                                CsvReader.countMatches(
                                        file,
                                        keyword);

                        preview =
                                CsvReader.getPreview(
                                        file,
                                        keyword);
                    }
                }

                // PDF
                else if (name.endsWith(".pdf")) {

                    found =
                            PdfReader.contains(
                                    file,
                                    keyword);

                    if (found) {

                        count =
                                PdfReader.countMatches(
                                        file,
                                        keyword);

                        preview =
                                PdfReader.getPreview(
                                        file,
                                        keyword);
                    }
                }

                // DOCX
                else if (name.endsWith(".docx")) {

                    found =
                            DocxReader.contains(
                                    file,
                                    keyword);

                    if (found) {

                        count =
                                DocxReader.countMatches(
                                        file,
                                        keyword);

                        preview =
                                DocxReader.getPreview(
                                        file,
                                        keyword);
                    }
                }

                if (found) {

                    resultFiles.add(file);

                    rankedResults.add(
                            new SearchResult(
                                    file,
                                    count,
                                    preview));
                }
            }
        }

        Collections.sort(
                rankedResults,

                new Comparator<SearchResult>() {

                    @Override
                    public int compare(
                            SearchResult a,
                            SearchResult b) {

                        return Integer.compare(
                                b.getMatches(),
                                a.getMatches());
                    }
                });
    }
}