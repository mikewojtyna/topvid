package pl.wojtyna.topvid.store;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;
import pl.wojtyna.topvid.patterns.DecoratorPattern;

import java.util.Optional;

@DecoratorPattern
public class EventPublishingVideoStore implements VideoStore {

    @NonNull
    private final VideoStore videoStore;
    @NonNull
    private final DomainEventPublisher domainEventPublisher;

    public EventPublishingVideoStore(@NonNull VideoStore videoStore,
                                     @NonNull DomainEventPublisher domainEventPublisher) {
        this.videoStore = videoStore;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void store(@NonNull Video video) {
        videoStore.store(video);
        domainEventPublisher.publish(new VideoStored());
    }

    @Override
    public Optional<byte[]> contentOf(@NonNull String name) {
        return videoStore.contentOf(name);
    }
}
