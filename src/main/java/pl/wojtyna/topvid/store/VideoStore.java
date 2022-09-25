package pl.wojtyna.topvid.store;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.StrategyPattern;

import java.util.Optional;


@StrategyPattern
public interface VideoStore {

    void store(@NonNull Video video);

    Optional<byte[]> contentOf(@NonNull String name);
}
