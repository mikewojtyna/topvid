package pl.wojtyna.tasks.cv;

import java.nio.file.Path;
import java.util.List;

public class CvParser {

    private final List<CvBuilder> cvBuilderList;

    public CvParser(List<CvBuilder> cvBuilderList) {
        this.cvBuilderList = cvBuilderList;
    }

    public void parse(Path path) {
        parseAFile(allLines(path));
    }

    private String allLines(Path path) {
        return null;
    }

    private void parseAFile(String line) {
        if (isHeader(line)) {
            cvBuilderList.forEach(cvBuilder -> cvBuilder.handleHeader(line));
        }
        if (isFooter(line)) {
            cvBuilderList.forEach(cvBuilder -> cvBuilder.handleFooter(line));
        }
        if (isParagraph(line)) {
            cvBuilderList.forEach(cvBuilder -> cvBuilder.handleParagraph(line));
        }
        if (isLastLine(line)) {
            cvBuilderList.forEach(cvBuilder -> cvBuilder.build());
        }
    }

    private boolean isLastLine(String line) {
        return false;
    }

    private boolean isParagraph(String line) {
        return false;
    }

    private boolean isFooter(String line) {
    }

    private boolean isHeader(String line) {
    }
}
