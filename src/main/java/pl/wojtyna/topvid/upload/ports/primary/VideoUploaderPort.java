package pl.wojtyna.topvid.upload.ports.primary;

import lombok.NonNull;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;
import pl.wojtyna.topvid.patterns.PrimaryPortPattern;
import pl.wojtyna.topvid.upload.domain.Uploader;
import pl.wojtyna.topvid.upload.domain.Video;
import pl.wojtyna.topvid.upload.domain.VideoUploader;

@PrimaryPortPattern
public class VideoUploaderPort {

    @NonNull
    private final VideoUploader videoUploader;
    @NonNull
    private final DomainEventPublisher eventPublisher;

    public VideoUploaderPort(@NonNull VideoUploader videoUploader, @NonNull DomainEventPublisher eventPublisher) {
        this.videoUploader = videoUploader;
        this.eventPublisher = eventPublisher;
    }

    public void upload(@NonNull Video video, @NonNull Uploader identity) {
        var events = videoUploader.upload(video, identity);
        events.publish(eventPublisher);
    }
}
