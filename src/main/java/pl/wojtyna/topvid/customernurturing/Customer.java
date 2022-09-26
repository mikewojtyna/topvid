package pl.wojtyna.topvid.customernurturing;

import pl.wojtyna.topvid.patterns.VisitorPattern;

@VisitorPattern
public interface Customer extends Visitable {

    String firstName();

    String lastName();

    String email();
}
