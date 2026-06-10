package Readers;

import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader {

    // Check if keyword exists
    public static boolean contains(
            File file,
            String keyword) {

        try (PDDocument doc =
                     Loader.loadPDF(file)) {

            String text =
                    new PDFTextStripper()
                            .getText(doc)
                            .toLowerCase();

            return text.contains(keyword);

        } catch (Exception e) {

            return false;
        }
    }

    // Count occurrences
    public static int countMatches(
            File file,
            String keyword) {

        try (PDDocument doc =
                     Loader.loadPDF(file)) {

            String text =
                    new PDFTextStripper()
                            .getText(doc)
                            .toLowerCase();

            int count = 0;
            int index = 0;

            while ((index =
                    text.indexOf(keyword, index))
                    != -1) {

                count++;
                index += keyword.length();
            }

            return count;

        } catch (Exception e) {

            return 0;
        }
    }

    // Get preview text
    public static String getPreview(
            File file,
            String keyword) {

        try (PDDocument doc =
                     Loader.loadPDF(file)) {

            String text =
                    new PDFTextStripper()
                            .getText(doc);

            String lower =
                    text.toLowerCase();

            int pos =
                    lower.indexOf(keyword);

            if (pos == -1) {
                return "";
            }

            int start =
                    Math.max(0, pos - 30);

            int end =
                    Math.min(
                            text.length(),
                            pos + keyword.length() + 50);

            return text.substring(start, end)
                    .replace("\n", " ");

        } catch (Exception e) {

            return "";
        }
    }
}