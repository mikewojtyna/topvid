package pl.wojtyna.topvid.store;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ChainOfResponsibilityPattern;

import java.util.Optional;

@ChainOfResponsibilityPattern
public class LinkVideoContentResolver implements VideoContentResolver {

    @NonNull
    private final VideoContentResolver handler;
    @NonNull
    private final VideoContentResolver next;

    public LinkVideoContentResolver(@NonNull VideoContentResolver handler, @NonNull VideoContentResolver next) {
        this.handler = handler;
        this.next = next;
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        return handler.contentOf(name).or(() -> next.contentOf(name));
    }
}
