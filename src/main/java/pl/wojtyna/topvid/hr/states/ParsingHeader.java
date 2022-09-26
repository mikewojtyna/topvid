package pl.wojtyna.topvid.hr.states;

import pl.wojtyna.topvid.hr.CvParserContext;
import pl.wojtyna.topvid.hr.CvParserState;
import pl.wojtyna.topvid.patterns.StatePattern;

@StatePattern
public class ParsingHeader implements CvParserState {

    private final CvParserContext cvParserContext;

    public ParsingHeader(CvParserContext cvParserContext) {
        this.cvParserContext = cvParserContext;
    }

    @Override
    public void handle(String line) {
        cvParserContext.executeOnAllBuilders(cvBuilder -> cvBuilder.handleHeader(line));
    }
}
