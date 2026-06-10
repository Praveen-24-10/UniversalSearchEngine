package Utils;

import java.awt.Desktop;
import java.io.File;

public class FileOpener {

    public static void openFile(String path) {

        try {

            File file = new File(path);

            if (file.exists()) {

                Desktop.getDesktop().open(file);

            } else {

                System.out.println("File not found.");
            }

        } catch (Exception e) {

            System.out.println("Cannot open file.");
        }
    }
}