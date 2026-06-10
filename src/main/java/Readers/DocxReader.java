package Readers;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxReader {

    // Check if keyword exists
    public static boolean contains(
            File file,
            String keyword) {

        try (
                FileInputStream fis =
                        new FileInputStream(file);

                XWPFDocument doc =
                        new XWPFDocument(fis)
        ) {

            StringBuilder text =
                    new StringBuilder();

            for (XWPFParagraph p :
                    doc.getParagraphs()) {

                text.append(p.getText())
                        .append(" ");
            }

            return text.toString()
                    .toLowerCase()
                    .contains(keyword);

        } catch (Exception e) {

            return false;
        }
    }

    // Count occurrences
    public static int countMatches(
            File file,
            String keyword) {

        try (
                FileInputStream fis =
                        new FileInputStream(file);

                XWPFDocument doc =
                        new XWPFDocument(fis)
        ) {

            StringBuilder text =
                    new StringBuilder();

            for (XWPFParagraph p :
                    doc.getParagraphs()) {

                text.append(p.getText())
                        .append(" ");
            }

            String content =
                    text.toString()
                            .toLowerCase();

            int count = 0;
            int index = 0;

            while ((index =
                    content.indexOf(keyword, index))
                    != -1) {

                count++;
                index += keyword.length();
            }

            return count;

        } catch (Exception e) {

            return 0;
        }
    }

    // Preview text around first match
    public static String getPreview(
            File file,
            String keyword) {

        try (
                FileInputStream fis =
                        new FileInputStream(file);

                XWPFDocument doc =
                        new XWPFDocument(fis)
        ) {

            StringBuilder text =
                    new StringBuilder();

            for (XWPFParagraph p :
                    doc.getParagraphs()) {

                text.append(p.getText())
                        .append(" ");
            }

            String content =
                    text.toString();

            String lower =
                    content.toLowerCase();

            int pos =
                    lower.indexOf(keyword);

            if (pos == -1) {
                return "";
            }

            int start =
                    Math.max(0, pos - 30);

            int end =
                    Math.min(
                            content.length(),
                            pos + keyword.length() + 50);

            return content.substring(start, end);

        } catch (Exception e) {

            return "";
        }
    }
}