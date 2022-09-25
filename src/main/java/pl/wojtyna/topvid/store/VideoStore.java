package pl.wojtyna.topvid.store;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.ServicePattern;
import pl.wojtyna.topvid.patterns.StrategyPattern;

import java.util.Optional;

@ServicePattern
@StrategyPattern
public interface VideoStore {

    void store(@NonNull Video video);

    Optional<byte[]> contentOf(@NonNull String name);
}
