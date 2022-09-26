package pl.wojtyna.topvid.store;

import lombok.NonNull;
import pl.wojtyna.topvid.patterns.DecoratorPattern;
import pl.wojtyna.topvid.patterns.PrimaryPortPattern;
import pl.wojtyna.topvid.patterns.StrategyPattern;


@StrategyPattern
@PrimaryPortPattern
@DecoratorPattern
public interface VideoStore extends VideoContentResolver {

    void store(@NonNull Video video);
}
