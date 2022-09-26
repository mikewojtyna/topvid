package pl.wojtyna.topvid.hr;

import pl.wojtyna.topvid.patterns.StatePattern;

import java.util.function.Consumer;

@StatePattern
public interface CvParserContext {

    void executeOnAllBuilders(Consumer<CvBuilder> action);
}
