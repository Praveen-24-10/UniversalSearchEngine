package Search;

import java.io.File;

public class SearchResult {

    private File file;
    private int matches;
    private String preview;

    public SearchResult(
            File file,
            int matches,
            String preview) {

        this.file = file;
        this.matches = matches;
        this.preview = preview;
    }

    public File getFile() {

        return file;
    }

    public int getMatches() {

        return matches;
    }

    public String getPreview() {

        return preview;
    }

    public void setFile(File file) {

        this.file = file;
    }

    public void setMatches(int matches) {

        this.matches = matches;
    }

    public void setPreview(String preview) {

        this.preview = preview;
    }

    @Override
    public String toString() {

        return "SearchResult{" +
                "file=" + file.getName() +
                ", matches=" + matches +
                ", preview='" + preview + '\'' +
                '}';
    }
}