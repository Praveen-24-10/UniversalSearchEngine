package Search;

import java.io.File;

public class FileNameSearcher {

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

            else if (file.getName()
                    .toLowerCase()
                    .contains(keyword)) {

                System.out.println("\n===== FILE FOUND =====");
                System.out.println("Name : "
                        + file.getName());

                System.out.println("Path : "
                        + file.getAbsolutePath());
            }
        }
    }
}