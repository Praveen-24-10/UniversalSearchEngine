package Search;

import java.io.File;

public class FileDateSearcher {

    public static void search(
            File folder,
            long days) {

        File[] files = folder.listFiles();

        if (files == null)
            return;

        long currentTime =
                System.currentTimeMillis();

        long limit =
                days * 24L * 60L * 60L * 1000L;

        for (File file : files) {

            if (file.isDirectory()) {

                search(file, days);
            }

            else {

                long diff =
                        currentTime
                                - file.lastModified();

                if (diff <= limit) {

                    System.out.println(
                            "\n===== FILE FOUND =====");

                    System.out.println(
                            "File Name : "
                                    + file.getName());

                    System.out.println(
                            "Path : "
                                    + file.getAbsolutePath());
                }
            }
        }
    }
}