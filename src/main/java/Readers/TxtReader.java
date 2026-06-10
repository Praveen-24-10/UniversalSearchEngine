package Readers;

import java.io.File;
import java.util.Scanner;

public class TxtReader {

    // Check if keyword exists
    public static boolean contains(
            File file,
            String keyword) {

        try (Scanner reader =
                     new Scanner(file)) {

            while (reader.hasNextLine()) {

                String line =
                        reader.nextLine()
                                .toLowerCase();

                if (line.contains(keyword)) {

                    return true;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Cannot read TXT: "
                            + file.getName());
        }

        return false;
    }

    // Count occurrences
    public static int countMatches(
            File file,
            String keyword) {

        int count = 0;

        try (Scanner reader =
                     new Scanner(file)) {

            while (reader.hasNextLine()) {

                String line =
                        reader.nextLine()
                                .toLowerCase();

                if (line.contains(keyword)) {

                    count++;
                }
            }

        } catch (Exception e) {

            return 0;
        }

        return count;
    }

    // Get first matching line
    public static String getPreview(
            File file,
            String keyword) {

        try (Scanner reader =
                     new Scanner(file)) {

            while (reader.hasNextLine()) {

                String line =
                        reader.nextLine();

                if (line.toLowerCase()
                        .contains(keyword)) {

                    return line;
                }
            }

        } catch (Exception e) {

            return "";
        }

        return "";
    }

    // Show all matching lines with line numbers
    public static void printMatches(
            File file,
            String keyword) {

        try (Scanner reader =
                     new Scanner(file)) {

            int lineNo = 1;

            while (reader.hasNextLine()) {

                String line =
                        reader.nextLine();

                if (line.toLowerCase()
                        .contains(keyword)) {

                    System.out.println(
                            "Line "
                                    + lineNo
                                    + " : "
                                    + line);
                }

                lineNo++;
            }

        } catch (Exception e) {

            System.out.println(
                    "Cannot read TXT: "
                            + file.getName());
        }
    }
}