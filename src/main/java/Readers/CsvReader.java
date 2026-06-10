package Readers;

import java.io.File;
import java.util.Scanner;

public class CsvReader {

    // Check if keyword exists
    public static boolean contains(
            File file,
            String keyword) {

        try (Scanner sc =
                     new Scanner(file)) {

            while (sc.hasNextLine()) {

                String line =
                        sc.nextLine()
                                .toLowerCase();

                if (line.contains(keyword)) {

                    return true;
                }
            }

        } catch (Exception e) {

            return false;
        }

        return false;
    }

    // Count occurrences
    public static int countMatches(
            File file,
            String keyword) {

        int count = 0;

        try (Scanner sc =
                     new Scanner(file)) {

            while (sc.hasNextLine()) {

                String line =
                        sc.nextLine()
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

        try (Scanner sc =
                     new Scanner(file)) {

            while (sc.hasNextLine()) {

                String line =
                        sc.nextLine();

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
}