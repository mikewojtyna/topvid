package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.StatePattern;

@StatePattern
public interface CvParserState {

    void handle(String line);
}
