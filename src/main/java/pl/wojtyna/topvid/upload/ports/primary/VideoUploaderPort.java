package pl.wojtyna.topvid.upload.ports.primary;

import lombok.NonNull;
import pl.wojtyna.topvid.common.commandinvoker.CommandInvoker;
import pl.wojtyna.topvid.common.domain.DomainEventPublisher;
import pl.wojtyna.topvid.patterns.CommandPattern;
import pl.wojtyna.topvid.patterns.PrimaryPortPattern;
import pl.wojtyna.topvid.upload.domain.Uploader;
import pl.wojtyna.topvid.upload.domain.Video;
import pl.wojtyna.topvid.upload.domain.VideoUploader;

@PrimaryPortPattern
@CommandPattern
public class VideoUploaderPort {

    @NonNull
    private final VideoUploader videoUploader;
    @NonNull
    private final DomainEventPublisher eventPublisher;
    @NonNull
    private final CommandInvoker commandInvoker;

    public VideoUploaderPort(@NonNull VideoUploader videoUploader, @NonNull DomainEventPublisher eventPublisher,
                             @NonNull CommandInvoker commandInvoker) {
        this.videoUploader = videoUploader;
        this.eventPublisher = eventPublisher;
        this.commandInvoker = commandInvoker;
    }

    public void upload(@NonNull Video video, @NonNull Uploader identity) {
        commandInvoker.invoke(() -> {
            var events = videoUploader.upload(video, identity);
            events.publish(eventPublisher);
        });
    }
}
