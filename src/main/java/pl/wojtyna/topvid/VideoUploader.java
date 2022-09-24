package pl.wojtyna.topvid;

import lombok.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class VideoUploader {

    private int uploadedVideos;

    DomainEvents upload(@NonNull Video video, @NonNull Uploader george) {
        if (video.size() > 256 || new String(video.content(),
                                             StandardCharsets.UTF_8).matches("^.*violen.*") || uploadedVideos >= george.limit()) {
            return new DomainEvents(List.of(new VideoRejected()));
        }
        uploadedVideos++;
        return new DomainEvents(List.of(new VideoUploaded()));
    }
}
