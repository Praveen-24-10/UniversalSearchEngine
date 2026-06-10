package Search;

import java.io.File;
import java.util.HashMap;

public class DuplicateFinder {

    public static String getDuplicates(
            File folder) {

        HashMap<Long, File> files =
                new HashMap<>();

        StringBuilder result =
                new StringBuilder();

        find(
                folder,
                files,
                result);

        if (result.length() == 0) {

            return "No duplicate files found.";
        }

        return result.toString();
    }

    public static void findDuplicates(
            File folder) {

        System.out.println(
                getDuplicates(folder));
    }

    private static void find(
            File folder,
            HashMap<Long, File> files,
            StringBuilder result) {

        File[] list =
                folder.listFiles();

        if (list == null) {
            return;
        }

        for (File file : list) {

            if (file.isDirectory()) {

                find(
                        file,
                        files,
                        result);
            }

            else {

                long size =
                        file.length();

                if (files.containsKey(size)) {

                    result.append(
                            "===== DUPLICATE FOUND =====\n\n");

                    result.append(
                            "File 1 : ")
                            .append(
                                    files.get(size)
                                            .getAbsolutePath())
                            .append("\n");

                    result.append(
                            "File 2 : ")
                            .append(
                                    file.getAbsolutePath())
                            .append("\n");

                    result.append(
                            "Size   : ")
                            .append(size)
                            .append(" bytes\n");

                    result.append(
                            "-----------------------------------\n\n");
                }

                else {

                    files.put(
                            size,
                            file);
                }
            }
        }
    }
}