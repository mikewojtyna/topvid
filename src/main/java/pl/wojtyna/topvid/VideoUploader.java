package pl.wojtyna.topvid;

import lombok.NonNull;

import java.util.List;

public class VideoUploader {

    DomainEvents upload(@NonNull Video video) {
        if (video.size() > 256) {
            return new DomainEvents(List.of(new VideoRejected()));
        }
        return new DomainEvents(List.of(new VideoUploaded()));
    }
}
