package pl.wojtyna.topvid.store.resolvers;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ChainOfResponsibilityPattern;
import pl.wojtyna.topvid.store.VideoContentResolver;

import java.util.Optional;

@ChainOfResponsibilityPattern
public class DelegatingVideoStoreContentResolver implements VideoContentResolver {

    @NonNull
    private final VideoContentResolver delegatee;

    public DelegatingVideoStoreContentResolver(@NonNull VideoContentResolver delegatee) {
        this.delegatee = delegatee;
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        return delegatee.contentOf(name);
    }
}
