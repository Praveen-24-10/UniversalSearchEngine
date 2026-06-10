import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Search.ContentSearcher;
import Search.DuplicateFinder;
import Search.FileDateSearcher;
import Search.FileNameSearcher;
import Search.FileSizeSearcher;
import Search.FileTypeSearcher;
import Search.SearchResult;

import Utils.Exporter;
import Utils.FileOpener;
import Utils.History;
import Utils.Statistics;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n==============================");
            System.out.println(" UNIVERSAL SEARCH ENGINE ");
            System.out.println("==============================");
            System.out.println("1. Search File Names");
            System.out.println("2. Search File Contents");
            System.out.println("3. Search By File Type");
            System.out.println("4. View Search History");
            System.out.println("5. File Statistics");
            System.out.println("6. Find Duplicate Files");
            System.out.println("7. Search By File Size");
            System.out.println("8. Search By Date");
            System.out.println("9. Export Last Search Results");
            System.out.println("10. Exit");

            System.out.print("\nEnter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter keyword: ");
                    String keyword1 =
                            sc.nextLine().toLowerCase();

                    History.add(keyword1);

                    FileNameSearcher.search(
                            new File("Documents"),
                            keyword1);

                    break;

                case 2:

                    System.out.print("Enter keyword: ");
                    String keyword2 =
                            sc.nextLine().toLowerCase();

                    History.add(keyword2);

                    long start =
                            System.currentTimeMillis();

                    ContentSearcher.clearResults();

                    ContentSearcher.search(
                            new File("Documents"),
                            keyword2);

                    long end =
                            System.currentTimeMillis();

                    ArrayList<SearchResult> rankedResults =
                            ContentSearcher.getRankedResults();

                    System.out.println(
                            "\n===== SEARCH RESULTS =====");

                    if (rankedResults.isEmpty()) {

                        System.out.println(
                                "No matches found.");
                    }

                    for (int i = 0;
                         i < rankedResults.size();
                         i++) {

                        SearchResult result =
                                rankedResults.get(i);

                        System.out.println(
                                "\nResult #" + (i + 1));

                        System.out.println(
                                "File Name   : "
                                        + result.getFile().getName());

                        System.out.println(
                                "Occurrences : "
                                        + result.getMatches());

                        System.out.println(
                                "Preview     : "
                                        + result.getPreview());

                        System.out.println(
                                "Path        : "
                                        + result.getFile()
                                        .getAbsolutePath());
                    }

                    System.out.println(
                            "\nTime Taken : "
                                    + (end - start)
                                    + " ms");

                    if (!rankedResults.isEmpty()) {

                        System.out.print(
                                "\nEnter result number to open (0 to skip): ");

                        int num = sc.nextInt();
                        sc.nextLine();

                        if (num > 0
                                && num <= rankedResults.size()) {

                            SearchResult selected =
                                    rankedResults.get(num - 1);

                            FileOpener.openFile(
                                    selected.getFile()
                                            .getAbsolutePath());
                        }
                    }

                    break;

                case 3:

                    System.out.print(
                            "Enter file type (txt/pdf/docx/csv/java): ");

                    String type =
                            sc.nextLine().toLowerCase();

                    FileTypeSearcher.search(
                            new File("Documents"),
                            type);

                    break;

                case 4:

                    History.show();
                    break;

               case 5:

    System.out.println(
            Statistics.getStatistics(
                    new File("Documents")));

    break;
                case 6:

                    DuplicateFinder.findDuplicates(
                            new File("Documents"));

                    break;

                case 7:

                    System.out.print(
                            "Enter minimum file size (KB): ");

                    long size =
                            sc.nextLong();

                    sc.nextLine();

                    FileSizeSearcher.search(
                            new File("Documents"),
                            size);

                    break;

                case 8:

                    System.out.println(
                            "\n1. Today");
                    System.out.println(
                            "2. Last 7 Days");
                    System.out.println(
                            "3. Last 30 Days");

                    System.out.print(
                            "Enter choice: ");

                    int dateChoice =
                            sc.nextInt();

                    sc.nextLine();

                    if (dateChoice == 1) {

                        FileDateSearcher.search(
                                new File("Documents"),
                                1);
                    }

                    else if (dateChoice == 2) {

                        FileDateSearcher.search(
                                new File("Documents"),
                                7);
                    }

                    else if (dateChoice == 3) {

                        FileDateSearcher.search(
                                new File("Documents"),
                                30);
                    }

                    else {

                        System.out.println(
                                "Invalid choice!");
                    }

                    break;

                case 9:

                    Exporter.exportResults(
                            ContentSearcher
                                    .getRankedResults());

                    break;

                case 10:

                    System.out.println(
                            "\nGoodbye!");

                    sc.close();
                    return;

                default:

                    System.out.println(
                            "Invalid Choice!");
            }
        }
    }
}