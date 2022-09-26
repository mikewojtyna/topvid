package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.hr.states.InitialState;
import pl.wojtyna.topvid.hr.states.ParsingFooter;
import pl.wojtyna.topvid.hr.states.ParsingHeader;
import pl.wojtyna.topvid.hr.states.ParsingParagraph;
import pl.wojtyna.topvid.patterns.BuilderPattern;
import pl.wojtyna.topvid.patterns.StatePattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Consumer;

@BuilderPattern
@StatePattern
public class CvParser implements CvParserContext {

    private final Iterable<CvBuilder> builders;
    private CvParserState state;

    public CvParser(CvBuilder... builders) {
        this.builders = List.of(builders);
        state = new InitialState();
    }

    public void parse(InputStream content) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(content))) {
            reader.lines().forEach(line -> {
                if (isHeader(line)) {
                    state = new ParsingHeader(this);
                }
                else if (isParagraph(line)) {
                    state = new ParsingParagraph(this);
                }
                else if (isFooter(line)) {
                    state = new ParsingFooter(this);
                }
                else if (isRegularLine(line)) {
                    state.handle(line);
                }
            });
        }
    }

    @Override
    public void executeOnAllBuilders(Consumer<CvBuilder> action) {
        builders.forEach(action);
    }

    private boolean isRegularLine(String line) {
        return !isSpecial(line) && !line.isBlank();
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
