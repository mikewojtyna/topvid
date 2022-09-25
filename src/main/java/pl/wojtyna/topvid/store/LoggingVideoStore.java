package pl.wojtyna.topvid.store;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.wojtyna.topvid.patterns.DecoratorPattern;

import java.util.Optional;

@DecoratorPattern
@Slf4j
public class LoggingVideoStore implements VideoStore {

    @NonNull
    private final VideoStore videoStore;

    public LoggingVideoStore(@NonNull VideoStore videoStore) {
        this.videoStore = videoStore;
    }

    @Override
    public void store(@NonNull Video video) {
        log.info("Storing video {}.", video);
        videoStore.store(video);
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        log.info("Retrieving content of video named {}.", name);
        var maybeContent = videoStore.contentOf(name);
        maybeContent.ifPresent(content -> log.info("{} bytes of content retrieved.", content.length));
        return maybeContent;
    }
}
