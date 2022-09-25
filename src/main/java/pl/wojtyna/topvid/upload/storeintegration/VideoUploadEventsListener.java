package pl.wojtyna.topvid.upload.storeintegration;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.DomainEvent;
import pl.wojtyna.topvid.common.domain.DomainEventListener;
import pl.wojtyna.topvid.patterns.AclPattern;
import pl.wojtyna.topvid.store.Video;
import pl.wojtyna.topvid.store.VideoStore;
import pl.wojtyna.topvid.upload.domain.VideoUploaded;

@AclPattern
public class VideoUploadEventsListener implements DomainEventListener {

    @NonNull
    private final VideoStore videoStore;

    public VideoUploadEventsListener(@NonNull VideoStore videoStore) {
        this.videoStore = videoStore;
    }

    @Override
    public void on(@NonNull DomainEvent event) {
        if (event instanceof VideoUploaded videoUploaded) {
            videoStore.store(new Video(videoUploaded.name(),
                                       videoUploaded.size(),
                                       videoUploaded.uploader().id(),
                                       videoUploaded.content())); // ACL translation between Upload and Store contexts
        }
    }

    @Override
    public <E extends DomainEvent> boolean supports(@NonNull Class<E> type) {
        return VideoUploaded.class.isAssignableFrom(type);
    }
}
