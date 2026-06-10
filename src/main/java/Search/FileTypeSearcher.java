package Search;

import java.io.File;

public class FileTypeSearcher {

    public static void search(
            File folder,
            String type) {

        File[] files = folder.listFiles();

        if (files == null)
            return;

        for (File file : files) {

            if (file.isDirectory()) {

                search(file, type);
            }

            else if (file.getName()
                    .toLowerCase()
                    .endsWith("." + type.toLowerCase())) {

                System.out.println("\n===== FILE FOUND =====");
                System.out.println("Name : "
                        + file.getName());

                System.out.println("Path : "
                        + file.getAbsolutePath());
            }
        }
    }
}