package Search;

import java.io.File;

public class FileSizeSearcher {

    public static void search(
            File folder,
            long minSizeKB) {

        File[] files = folder.listFiles();

        if (files == null)
            return;

        for (File file : files) {

            if (file.isDirectory()) {

                search(file, minSizeKB);
            }

            else {

                long sizeKB =
                        file.length() / 1024;

                if (sizeKB >= minSizeKB) {

                    System.out.println(
                            "\n===== FILE FOUND =====");

                    System.out.println(
                            "File Name : "
                                    + file.getName());

                    System.out.println(
                            "Size : "
                                    + sizeKB
                                    + " KB");

                    System.out.println(
                            "Path : "
                                    + file.getAbsolutePath());
                }
            }
        }
    }
}