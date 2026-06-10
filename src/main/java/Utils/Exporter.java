package Utils;

import java.io.FileWriter;
import java.util.ArrayList;

import Search.SearchResult;

public class Exporter {

    public static void exportResults(
            ArrayList<SearchResult> results) {

        try (FileWriter writer =
                     new FileWriter(
                             "search_results.txt")) {

            writer.write(
                    "===== SEARCH RESULTS =====\n\n");

            for (int i = 0;
                 i < results.size();
                 i++) {

                SearchResult result =
                        results.get(i);

                writer.write(
                        "Result #" + (i + 1) + "\n");

                writer.write(
                        "File Name : "
                                + result.getFile()
                                .getName() + "\n");

                writer.write(
                        "Occurrences : "
                                + result.getMatches()
                                + "\n");

                writer.write(
                        "Preview : "
                                + result.getPreview()
                                + "\n");

                writer.write(
                        "Path : "
                                + result.getFile()
                                .getAbsolutePath()
                                + "\n");

                writer.write(
                        "---------------------------------\n");
            }

            System.out.println(
                    "\nResults exported to search_results.txt");

        } catch (Exception e) {

            System.out.println(
                    "Export failed.");

            e.printStackTrace();
        }
    }
}