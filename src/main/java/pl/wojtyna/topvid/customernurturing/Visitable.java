package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

@VisitorPattern
public interface Visitable {

    void accept(Visitor visitor);
}
