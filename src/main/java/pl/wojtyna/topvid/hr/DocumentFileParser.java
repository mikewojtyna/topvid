package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.BuilderPattern;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@BuilderPattern
public class DocumentFileParser {

    private final Iterable<CvBuilder> builders;

    public DocumentFileParser(CvBuilder... builders) {
        this.builders = List.of(builders);
    }

    public void parse(InputStream content) {
        List<String> lines =
            new BufferedReader(new InputStreamReader(content))
                .lines().toList();
        boolean header = false;
        boolean paragraph = false;
        boolean footer = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            if (isHeader(line)) {
                header = true;
            }
            if (isParagraph(line)) {
                if (header) {
                    handleHeader(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
                if (paragraph) {
                    handleParagraph(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
                footer = false;
                header = false;
                paragraph = true;
            }
            if (isFooter(line)) {
                if (paragraph) {
                    handleParagraph(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
                footer = true;
                header = false;
                paragraph = false;
            }
            if (!isSpecial(line)) {
                stringBuilder.append(line);
            }
        }
        handleFooter(stringBuilder);
    }

    private void handleFooter(StringBuilder stringBuilder) {
        for (CvBuilder builder : builders) {
            builder.handleFooter(stringBuilder.toString());
        }
    }

    private void handleParagraph(StringBuilder stringBuilder) {
        for (CvBuilder builder : builders) {
            builder.handleParagraph(stringBuilder.toString());
        }
    }

    private void handleHeader(StringBuilder stringBuilder) {
        for (CvBuilder builder : builders) {
            builder.handleHeader(stringBuilder.toString());
        }
    }

    private boolean isSpecial(String line) {
        return isHeader(line) || isParagraph(line) || isFooter(line);
    }

    private boolean isFooter(String line) {
        return line.equals("::Footer::");
    }

    private boolean isParagraph(String line) {
        return line.equals("::Paragraph::");
    }

    private boolean isHeader(String line) {
        return line.equals("::Header::");
    }
}
