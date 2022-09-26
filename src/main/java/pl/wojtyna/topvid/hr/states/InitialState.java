package pl.wojtyna.topvid.hr.states;

import pl.wojtyna.topvid.hr.CvParserState;
import pl.wojtyna.topvid.patterns.StatePattern;

@StatePattern
public class InitialState implements CvParserState {

    @Override
    public void handle(String line) {
        // do nothing - we are in the initial state
    }
}
